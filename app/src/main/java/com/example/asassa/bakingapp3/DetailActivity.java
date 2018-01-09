package com.example.asassa.bakingapp3;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            if (getIntent().getExtras().getParcelable("step") != null) {
                DetailsRecipeFragment fragment = new DetailsRecipeFragment();
                fragmentManager.beginTransaction().add(R.id.details_fragment, fragment).commit();
            } else if (getIntent().getExtras().getParcelableArrayList("ingedients") != null) {
                IngredientFragment fragment = new IngredientFragment();
                fragmentManager.beginTransaction().add(R.id.details_fragment, fragment).commit();
            }
        }
    }
}
