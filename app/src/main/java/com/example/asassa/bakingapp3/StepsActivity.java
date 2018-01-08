package com.example.asassa.bakingapp3;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asassa.bakingapp3.Utils.Ingredient;
import com.example.asassa.bakingapp3.Utils.Recipe;
import com.example.asassa.bakingapp3.Utils.Step;

import java.util.List;

public class StepsActivity extends AppCompatActivity implements MasterListFragment.OnStepClickListener,
        MasterListFragment.OnIngredientsClickListener {

    boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        if (findViewById(R.id.layout_two_pane)!=null)
        {
            mTwoPane = true;
            if (savedInstanceState == null) {
                Recipe recipe = getIntent().getExtras().getParcelable("recipe");
                DetailsRecipeFragment fragment = new DetailsRecipeFragment();
                fragment.setStepDetails(recipe.steps().get(0));
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.details_fragment, fragment).commit();
            }
        }
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
            intent.putExtra("step", step);
            startActivity(intent);
        }
    }

    @Override
    public void onIngerdientsClick(List<Ingredient> ingredients) {

        /*if (mTwoPane)
        {
            DetailsRecipeFragment fragment = new DetailsRecipeFragment();
            fragment.setStepDetails(step);

            getSupportFragmentManager().beginTransaction().replace(R.id.details_fragment,fragment).commit();
        }
        else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("step", step);
            startActivity(intent);
        }*/
    }
}
