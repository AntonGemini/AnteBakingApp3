package com.example.asassa.bakingapp3;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView;

import com.example.asassa.bakingapp3.Utils.ActivityIdlingResource;
import com.example.asassa.bakingapp3.Utils.NetworkProvider;
import com.example.asassa.bakingapp3.Utils.Recipe;
import com.example.asassa.bakingapp3.Utils.RecipesAdapter;
import com.example.asassa.bakingapp3.Utils.RecipesLoader;
import com.example.asassa.bakingapp3.Utils.Step;

import java.net.URI;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Recipe>>, RecipesAdapter.OnRecipeClickListener {

    List<Recipe> mRecipes = null;
    Context mContext = null;
    private static int LOADER_ID = 1;
    GridLayoutManager gridLayoutManager;
    RecipesAdapter adapter;
    private int firstVisible = 0;
    RecyclerView recipesRecycler;

    ActivityIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        if (savedInstanceState != null) {
            firstVisible = savedInstanceState.getInt("firstVisible", 0);
        }
        getSupportLoaderManager().initLoader(LOADER_ID,null, this);

    }

    @Override
    public Loader<List<Recipe>> onCreateLoader(int id, Bundle args) {
        if (mIdlingResource!= null) {
            mIdlingResource.setIdleState(false);
        }
        else
        {
            mIdlingResource = new ActivityIdlingResource();
            mIdlingResource.setIdleState(false);
        }
        return new RecipesLoader(getBaseContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Recipe>> loader, List<Recipe> data) {
        mRecipes = data;
        loadRecipes(mRecipes);
        if (firstVisible > 0)
        {
            recipesRecycler.scrollToPosition(firstVisible);
        }

        if(mIdlingResource != null){
            mIdlingResource.setIdleState(true);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Recipe>> loader) {

    }

    public void loadRecipes(List<Recipe> recipes)
    {
        recipesRecycler = (RecyclerView) findViewById(R.id.rv_recipes);
        adapter = new RecipesAdapter(this, recipes);

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        }
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        }
        recipesRecycler.setLayoutManager(gridLayoutManager);
        recipesRecycler.setAdapter(adapter);
        //gridLayoutManager.findFirstVisibleItemPosition();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        int visiblePosition = gridLayoutManager.findFirstVisibleItemPosition();
        savedInstanceState.putInt("firstVisible",visiblePosition);
    }

    @Override
    public void onRecipeClick(int recipeId) {
        Recipe recipe = mRecipes.get(recipeId);
        Intent intent = new Intent(this,StepsActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource()
    {
        if (mIdlingResource == null){
            mIdlingResource = new ActivityIdlingResource();
        }
        return mIdlingResource;
    }


}
