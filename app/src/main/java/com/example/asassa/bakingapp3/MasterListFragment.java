package com.example.asassa.bakingapp3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asassa.bakingapp3.Adapters.StepListAdapter;
import com.example.asassa.bakingapp3.Utils.Ingredient;
import com.example.asassa.bakingapp3.Utils.Recipe;
import com.example.asassa.bakingapp3.Utils.Step;

import java.util.List;

/**
 * Created by ASassa on 03.01.2018.
 */

public class MasterListFragment extends android.support.v4.app.Fragment {


    private int firstVisible;
    LinearLayoutManager linearLayoutManager;

    public interface OnIngredientsClickListener
    {
        void onIngerdientsClick(List<Ingredient> ingredients);
    }


    private OnIngredientsClickListener onIngredienstClickListener;
    private TextView textViewIngredients;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            onIngredienstClickListener = (OnIngredientsClickListener)context;
        }
        catch (Exception ex)
        {

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int visiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
        outState.putInt(getString(R.string.first_visible),visiblePosition);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master_list,container);
        RecyclerView recyclerView = view.findViewById(R.id.rv_steps);
        textViewIngredients = view.findViewById(R.id.tv_ingredients);
        Intent intent = getActivity().getIntent();

        if (intent!= null)
        {
            final Recipe recipe = intent.getExtras().getParcelable(getString(R.string.recipe_parcel));

            StepListAdapter stepList = new StepListAdapter(getContext(), recipe.steps());
            linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(stepList);

            textViewIngredients.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onIngredienstClickListener.onIngerdientsClick(recipe.ingredients());
                }
            });
        }
        if (savedInstanceState != null) {
            firstVisible = savedInstanceState.getInt(getString(R.string.first_visible), 0);
            recyclerView.scrollToPosition(firstVisible);
        }

        return view;
    }
}
