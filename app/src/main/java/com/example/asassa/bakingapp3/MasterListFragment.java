package com.example.asassa.bakingapp3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asassa.bakingapp3.Adapters.StepListAdapter;
import com.example.asassa.bakingapp3.Utils.Recipe;
import com.example.asassa.bakingapp3.Utils.Step;

/**
 * Created by ASassa on 03.01.2018.
 */

public class MasterListFragment extends android.support.v4.app.Fragment {

    public interface OnStepClickListener
    {
        void onStepClick(Step position);
    }

    private OnStepClickListener onStepClickListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*try{

        }*/

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master_list,container);
        Intent intent = getActivity().getIntent();
        if (intent!= null)
        {
            final Recipe recipe = intent.getExtras().getParcelable("recipe");
            ListView listView = view.findViewById(R.id.rv_steps);
            StepListAdapter stepList = new StepListAdapter(getContext(), recipe.steps());
            listView.setAdapter(stepList);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    onStepClickListener.onStepClick(recipe.steps().get(i));
                }
            });
        }

        return view;
    }


}
