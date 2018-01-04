package com.example.asassa.bakingapp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.asassa.bakingapp3.Adapters.StepListAdapter;
import com.example.asassa.bakingapp3.Utils.Recipe;

/**
 * Created by ASassa on 03.01.2018.
 */

public class MasterListFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master_list,container);
        Intent intent = getActivity().getIntent();
        if (intent!= null)
        {
            Recipe recipe = (Recipe) intent.getExtras().getParcelable("recipe");
            ListView listView = (ListView) view.findViewById(R.id.rv_steps);
            StepListAdapter stepList = new StepListAdapter(getContext(), recipe.steps());
            listView.setAdapter(stepList);
        }
        //Get a refernece to the GridView in the fragment_master_list.xml file


        return view;
    }
}
