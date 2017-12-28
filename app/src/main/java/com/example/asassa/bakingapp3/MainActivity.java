package com.example.asassa.bakingapp3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asassa.bakingapp3.Utils.NetworkProvider;
import com.example.asassa.bakingapp3.Utils.Recipe;

import java.net.URI;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Recipe> mRecipes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new RecipeRequest().execute();

    }

    class RecipeRequest extends AsyncTask<Void,Void,List<Recipe>>
    {
        @Override
        protected List<Recipe> doInBackground(Void... voids) {
            List<Recipe> recipes = NetworkProvider.getRecipesJSON("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
            return recipes;
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            mRecipes = recipes;
        }
    }
}
