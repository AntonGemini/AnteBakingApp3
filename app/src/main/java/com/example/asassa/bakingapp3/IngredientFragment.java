package com.example.asassa.bakingapp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.asassa.bakingapp3.Adapters.IngredientsAdapter;
import com.example.asassa.bakingapp3.Utils.Ingredient;

import java.util.ArrayList;

/**
 * Created by ASassa on 08.01.2018.
 */

public class IngredientFragment extends Fragment {

    private RecyclerView ingedientsView;
    private ArrayList<Ingredient> mIngredients;
    private LinearLayoutManager layoutManager;
    private int firstVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient,container,false);
        ingedientsView = view.findViewById(R.id.rv_ingredients);
        Intent intent = getActivity().getIntent();
        if(savedInstanceState!= null)
        {
            mIngredients = savedInstanceState.getParcelableArrayList(getString(R.string.ingedients_parcel));
            firstVisible = savedInstanceState.getInt(getString(R.string.first_visible), 0);
        }
        else if (mIngredients == null && intent.getExtras().getParcelableArrayList(getString(R.string.ingedients_parcel)) != null)
        {
            mIngredients = intent.getExtras().getParcelableArrayList(getString(R.string.ingedients_parcel));
        }

        IngredientsAdapter adapter = new IngredientsAdapter(getContext(),mIngredients);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);

        ingedientsView.setLayoutManager(layoutManager);
        ingedientsView.setAdapter(adapter);
        ingedientsView.scrollToPosition(firstVisible);
        return view;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients)
    {
        mIngredients = ingredients;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getString(R.string.ingedients_parcel),mIngredients);
        int visiblePosition = layoutManager.findFirstVisibleItemPosition();
        outState.putInt(getString(R.string.first_visible),visiblePosition);
    }
}
