package com.example.asassa.bakingapp3.Utils;

import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
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

    public static List<Recipe> getRecipesJSON(String url)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        String result = "";
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
            parseGson(result);
            return readRecipesArray(result);
        }
        catch(Exception ex)
        {
            Log.d("OKHTTP","Error connecting server");
        }
        return null;

    }

    public static void parseGson(String json)
    {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonTypeAdapterFactory.create())
                .create();
        Type type = new TypeToken<ArrayList<Recipe>>() {}.getType();
        List<Recipe> recipes = gson.fromJson(json,type);
        //Store list recipes first time in shared preferences.


    }


    public static List<Recipe> readRecipesArray(String jsonRecipes) throws IOException
    {
        //JSONObject jsonObject = JSONParser
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

    private static Recipe readRecipe(JsonReader reader) throws IOException
    {
        //Recipe recipe = new Recipe();
        Recipe.Builder recipeBuilder = Recipe.builder();
        reader.beginObject();
        while(reader.hasNext())
        {
            String name = reader.nextName();
            if (name.equals("id")){
                recipeBuilder.setId(reader.nextInt());
            }
            else if (name.equals("name")) {
                recipeBuilder.setName(reader.nextString());
            }
            else if (name.equals("ingredients")) {
                recipeBuilder.setIngredients(getReaderIngredients(reader));
            }
            else if (name.equals("steps")) {
                recipeBuilder.setSteps(getReaderSteps(reader));
            }
            else if (name.equals("servings")) {
                recipeBuilder.setServings(reader.nextInt());
            }
            else if (name.equals("image")) {
                recipeBuilder.setImage(reader.nextString());
            }
            else
            {
                reader.skipValue();
            }
        }
        reader.endObject();
        return recipeBuilder.build();

    }

    private static List<Ingredient> getReaderIngredients(JsonReader reader) throws IOException
    {
        //JSONArray
        reader.beginArray();
        List<Ingredient> ingredientList = new ArrayList<>();
        while (reader.hasNext())
        {
            Ingredient.Builder ingredientBuilder = Ingredient.builder();
            reader.beginObject();
            while (reader.hasNext())
            {
                String name = reader.nextName();
                if (name.equals("quantity")){
                    ingredientBuilder.setQuantity(reader.nextDouble());
                }
                else if (name.equals("measure")){
                    ingredientBuilder.setMeasure(reader.nextString());
                }
                else if (name.equals("ingredient")){
                    ingredientBuilder.setIngredient(reader.nextString());
                }
                else
                {
                    reader.skipValue();
                }
            }
            reader.endObject();
            ingredientList.add(ingredientBuilder.build());
        }
        reader.endArray();
        return ingredientList;
    }

    private static List<Step> getReaderSteps(JsonReader reader) throws IOException
    {

        reader.beginArray();
        List<Step> stepList = new ArrayList<>();
        while (reader.hasNext())
        {
            Step.Builder stepBuilder = Step.builder();
            reader.beginObject();
            while (reader.hasNext())
            {
                String name = reader.nextName();
                if (name.equals("id")){
                    stepBuilder.setId(reader.nextInt());
                }
                else if (name.equals("shortDescription")){
                    stepBuilder.setShortDescription(reader.nextString());
                }
                else if (name.equals("description")){
                    stepBuilder.setDescription(reader.nextString());
                }
                else if (name.equals("videoURL")){
                    stepBuilder.setVideoURL(reader.nextString());
                }
                else if (name.equals("videoURL")){
                    stepBuilder.setVideoURL(reader.nextString());
                }
                else if (name.equals("thumbnailURL")){
                    stepBuilder.setThumbnailURL(reader.nextString());
                }
                else
                {
                    reader.skipValue();
                }
            }
            reader.endObject();
            stepList.add(stepBuilder.build());
        }
        //recipe.setSteps(stepList);
        reader.endArray();
        return stepList;
    }





}
