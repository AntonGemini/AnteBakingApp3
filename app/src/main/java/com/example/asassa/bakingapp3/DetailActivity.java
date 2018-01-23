package com.example.asassa.bakingapp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class DetailActivity extends AppCompatActivity {

    private String actionBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            actionBarTitle = getIntent().getExtras().getString(getString(R.string.action_bar_title));
            getSupportActionBar().setTitle(actionBarTitle);
            if (getIntent().getExtras().getParcelable(getString(R.string.step_parcel)) != null) {
                DetailsRecipeFragment fragment = new DetailsRecipeFragment();
                fragmentManager.beginTransaction().add(R.id.details_fragment, fragment).commit();
            } else if (getIntent().getExtras().getParcelableArrayList(getString(R.string.ingedients_parcel)) != null) {
                IngredientFragment fragment = new IngredientFragment();
                fragmentManager.beginTransaction().add(R.id.details_fragment, fragment).commit();
            }
        }
        else
        {
            actionBarTitle = savedInstanceState.getString(getString(R.string.action_bar_title));
            getSupportActionBar().setTitle(actionBarTitle);
        }

        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.action_bar_title), actionBarTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
