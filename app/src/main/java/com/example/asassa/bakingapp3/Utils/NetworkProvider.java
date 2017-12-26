package com.example.asassa.bakingapp3.Utils;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASassa on 25.12.2017.
 */

public class NetworkProvider {

    public String getRecipesJSON(URL url)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        String result = "";
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        }
        catch(IOException ex)
        {
            Log.d("OKHTTP","Error connecting server");
        }
        return result;
    }

    public List<Recipe> readRecipesArray(String jsonRecipes) throws IOException
    {
        List<Recipe> recipes = new ArrayList<>();
        JsonReader reader = new JsonReader(new StringReader(jsonRecipes));
        reader.beginArray();
        while (reader.hasNext())
        {
            Recipe recipe = readRecipe(reader);
            recipes.add(recipe);
        }
        reader.endArray();
        return recipes;
    }

    private Recipe readRecipe(JsonReader reader) throws IOException
    {
        Recipe recipe = new Recipe();
        reader.beginObject();
        while(reader.hasNext())
        {
            String name = reader.nextName();
            if (name.equals("id")){
                recipe.setId(reader.nextInt());
            }
            else if (name.equals("name")) {
                recipe.setName(reader.nextString());
            }
            else if (name.equals("ingredients")) {
                recipe.setIngredients(getReaderIngredients(reader));
            }
            else if (name.equals("steps")) {
                recipe.setSteps(getReaderSteps(reader));
            }
            else if (reader.equals("servings")) {
                recipe.setServings(reader.nextInt());
            }
            else if (reader.equals("image")) {
                recipe.setImage(reader.nextString());
            }
        }
        reader.endObject();
        return recipe;

    }

    private List<Recipe.Ingredient>


}
