package com.example.asassa.bakingapp3;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.content.Loader;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.asassa.bakingapp3.Utils.ActivityIdlingResource;
import com.example.asassa.bakingapp3.Utils.ConnectionReciever;
import com.example.asassa.bakingapp3.Utils.Recipe;
import com.example.asassa.bakingapp3.Adapters.RecipesAdapter;
import com.example.asassa.bakingapp3.Utils.RecipesLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Recipe>>, ConnectionReciever.OnConnectionChangedReciever,
        RecipesAdapter.OnRecipeClickListener   {


    List<Recipe> mRecipes;
    Context mContext;
    private static int LOADER_ID = 1;
    GridLayoutManager mGridLayoutManager;
    int mFirstVisible = 0;
    RecyclerView mRecipesRecycler;
    ConnectionReciever connectionReciever;

    ActivityIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionReciever = new ConnectionReciever(this);

        mContext = this;
        if (savedInstanceState != null) {
            mFirstVisible = savedInstanceState.getInt(getString(R.string.first_visible), 0);
        }
        if (ConnectionReciever.checkConnection(this)) {
            getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        }
        else
        {
            Toast.makeText(this,getString(R.string.error_connecting),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectionReciever,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(connectionReciever);
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
        if (data!=null && data.size()>0) {
            mRecipes = data;
            loadRecipes(mRecipes);
            if (mFirstVisible > 0) {
                mRecipesRecycler.scrollToPosition(mFirstVisible);
            }
        }
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(true);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Recipe>> loader) {

    }

    public void loadRecipes(List<Recipe> recipes)
    {
        mRecipesRecycler = findViewById(R.id.rv_recipes);
        RecipesAdapter adapter = new RecipesAdapter(this, recipes);

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mGridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        }
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            mGridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        }
        mRecipesRecycler.setLayoutManager(mGridLayoutManager);
        mRecipesRecycler.setAdapter(adapter);

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (mGridLayoutManager!=null) {
            int visiblePosition = mGridLayoutManager.findFirstVisibleItemPosition();
            savedInstanceState.putInt(getString(R.string.first_visible), visiblePosition);
        }
    }

    @Override
    public void onRecipeClick(int recipeId) {
        Recipe recipe = mRecipes.get(recipeId);
        Intent intent = new Intent(this,StepsActivity.class);
        intent.putExtra(getString(R.string.recipe_parcel), recipe);
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


    @Override
    public void onConnectionChanged(boolean isConnected) {
        if (isConnected)
        {
            Toast.makeText(this, getString(R.string.connection_ok), Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, getString(R.string.error_connecting), Toast.LENGTH_LONG).show();
        }
    }

}
