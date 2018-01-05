package com.example.asassa.bakingapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asassa.bakingapp3.Utils.Recipe;
import com.example.asassa.bakingapp3.Utils.Step;

public class StepsActivity extends AppCompatActivity implements MasterListFragment.OnStepClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
    }

    @Override
    public void onStepClick(Step position) {
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("step",intent);
        startActivity(intent);
}
}
