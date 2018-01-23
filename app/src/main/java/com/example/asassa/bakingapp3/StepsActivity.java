package com.example.asassa.bakingapp3;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.asassa.bakingapp3.Utils.Ingredient;
import com.example.asassa.bakingapp3.Utils.Recipe;
import com.example.asassa.bakingapp3.Utils.Step;

import java.util.ArrayList;
import java.util.List;

public class StepsActivity extends AppCompatActivity implements MasterListFragment.OnStepClickListener,
        MasterListFragment.OnIngredientsClickListener {

    boolean mTwoPane = false;
    String titleActionBar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        Recipe recipe;
        if (savedInstanceState == null)
        {
            recipe = getIntent().getExtras().getParcelable(getString(R.string.recipe_parcel));
            titleActionBar = recipe.name();
            getSupportActionBar().setTitle(titleActionBar);
        }
        else {
            titleActionBar = savedInstanceState.getString(getString(R.string.action_bar_title));
            getSupportActionBar().setTitle(titleActionBar);
        }

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.layout_two_pane)!=null)
        {
            mTwoPane = true;
            if (savedInstanceState==null) {
                recipe = getIntent().getExtras().getParcelable(getString(R.string.recipe_parcel));
                DetailsRecipeFragment fragment = new DetailsRecipeFragment();
                fragment.setStepDetails(recipe.steps().get(0));
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.details_fragment, fragment).commit();
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStepClick(Step step) {

        if (mTwoPane)
        {
            DetailsRecipeFragment fragment = new DetailsRecipeFragment();
            fragment.setStepDetails(step);
            getSupportFragmentManager().beginTransaction().replace(R.id.details_fragment,fragment).commit();
        }
        else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(getString(R.string.step_parcel), step);
            intent.putExtra(getString(R.string.action_bar_title),titleActionBar);
            startActivity(intent);
        }
    }

    @Override
    public void onIngerdientsClick(List<Ingredient> ingredients) {

        if (mTwoPane)
        {
            IngredientFragment fragment = new IngredientFragment();
            fragment.setIngredients(new ArrayList(ingredients));
            getSupportFragmentManager().beginTransaction().replace(R.id.details_fragment,fragment).commit();
        }
        else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(getString(R.string.ingedients_parcel),new ArrayList(ingredients));
            intent.putExtra(getString(R.string.action_bar_title),titleActionBar);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.action_bar_title),titleActionBar);
    }
}
