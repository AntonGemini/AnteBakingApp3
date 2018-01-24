package com.example.asassa.bakingapp3.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
        String result;
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
            return parseGson(result);
        }
        catch(Exception ex)
        {
        }
        return null;

    }

    public static List<Recipe> parseGson(String json)
    {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonTypeAdapterFactory.create())
                .create();
        Type type = new TypeToken<ArrayList<Recipe>>() {}.getType();
        return gson.fromJson(json,type);
    }


}
