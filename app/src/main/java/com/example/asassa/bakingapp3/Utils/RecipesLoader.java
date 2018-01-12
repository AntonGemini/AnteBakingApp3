package com.example.asassa.bakingapp3.Utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by ASassa on 12.01.2018.
 */

public class RecipesLoader extends AsyncTaskLoader<List<Recipe>> {


    public RecipesLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Recipe> loadInBackground() {
        List<Recipe> recipes = NetworkProvider.getRecipesJSON("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
        return recipes;
    }
}
