package com.example.asassa.bakingapp3;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.content.Loader;
import android.support.v4.app.LoaderManager;
import android.widget.TextView;

import com.example.asassa.bakingapp3.Adapters.WidgetRecyclerAdapter;
import com.example.asassa.bakingapp3.Utils.Recipe;
import com.example.asassa.bakingapp3.Utils.RecipesLoader;
import com.google.gson.Gson;

import java.util.List;

/**
 * The configuration screen for the {@link IngredientsWidget IngredientsWidget} AppWidget.
 */
public class IngredientsWidgetConfigureActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Recipe>>, WidgetRecyclerAdapter.OnWidgetRecipeClick {

    private static final String PREFS_NAME = "com.example.asassa.bakingapp3.IngredientsWidget";
    public static final String PREF_PREFIX_KEY = "appwidget_";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    RecyclerView mAppWidgetList;
    List<Recipe> mRecipes;


    public IngredientsWidgetConfigureActivity() {
        super();
    }


    static void deleteTitlePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_KEY + appWidgetId);
        prefs.apply();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);
        setContentView(R.layout.ingredients_widget_configure);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        getSupportLoaderManager().initLoader(4,null,this);
    }


    @Override
    public Loader<List<Recipe>> onCreateLoader(int id, Bundle args) {
        return new RecipesLoader(getBaseContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Recipe>> loader, List<Recipe> data) {
        mRecipes = data;
        mAppWidgetList = findViewById(R.id.lv_widget_recipes);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        mAppWidgetList.setLayoutManager(linearLayoutManager);

        WidgetRecyclerAdapter adapter = new WidgetRecyclerAdapter(getBaseContext(),data, this);
        mAppWidgetList.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<Recipe>> loader) {

    }

    @Override
    public void OnRecipeClick(Recipe recipe) {
        saveWidgetList(mAppWidgetId,recipe);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getBaseContext());
        IngredientsWidget.updateAppWidget(getBaseContext(),appWidgetManager,mAppWidgetId);

        Intent resultIntent = new Intent();
        resultIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,mAppWidgetId);
        setResult(RESULT_OK,resultIntent);
        finish();
    }

    private void saveWidgetList(int mAppWidgetId, Recipe recipe) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Gson gson = new Gson();
        String recipeJson = gson.toJson(recipe);
        sharedPreferences.edit().putString(PREF_PREFIX_KEY + " " + mAppWidgetId,recipeJson).apply();
    }
}

