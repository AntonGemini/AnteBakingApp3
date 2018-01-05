package com.example.asassa.bakingapp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asassa.bakingapp3.Utils.Step;

/**
 * Created by ASassa on 05.01.2018.
 */

public class DetailsRecipeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_recipe,container);
        Intent intent = getActivity().getIntent();
        Step step = intent.getExtras().getParcelable("step");
        TextView tv = view.findViewById(R.id.tv_step_description);
        tv.setText(step.description());
        return view;
    }




}
