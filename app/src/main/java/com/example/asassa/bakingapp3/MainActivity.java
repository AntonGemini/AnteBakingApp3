package com.example.asassa.bakingapp3;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;

import com.example.asassa.bakingapp3.Utils.NetworkProvider;
import com.example.asassa.bakingapp3.Utils.Recipe;

import java.net.URI;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Recipe>> {

    List<Recipe> mRecipes = null;
    Context mContext = null;
    private static int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        //getLoaderManager().initLoader(LOADER_ID,null, this);
        getSupportLoaderManager().initLoader(LOADER_ID,null, this);
        //new RecipeRequest().execute();

    }

    @Override
    public Loader<List<Recipe>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<Recipe>>(mContext) {
            @Override
            public List<Recipe> loadInBackground() {
                List<Recipe> recipes = NetworkProvider.getRecipesJSON("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
                return recipes;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<Recipe>> loader, List<Recipe> data) {
        mRecipes = data;
    }

    @Override
    public void onLoaderReset(Loader<List<Recipe>> loader) {

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
