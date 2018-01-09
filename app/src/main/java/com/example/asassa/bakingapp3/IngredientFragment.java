package com.example.asassa.bakingapp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.asassa.bakingapp3.Adapters.IngredientsAdapter;
import com.example.asassa.bakingapp3.Utils.Ingredient;
import com.example.asassa.bakingapp3.Utils.Step;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by ASassa on 08.01.2018.
 */

public class IngredientFragment extends Fragment {

    ListView ingedientsView;
    List<Ingredient> mIngredients;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient,container);
        ingedientsView = view.findViewById(R.id.lv_ingredients);
        Intent intent = getActivity().getIntent();
        if (mIngredients == null && intent.getExtras().getParcelableArrayList("ingedients") != null)
        {
            mIngredients = intent.getExtras().getParcelableArrayList("ingedients");
        }
        IngredientsAdapter adapter = new IngredientsAdapter(getContext(),mIngredients);
        ingedientsView.setAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setIngredients(List<Ingredient> ingredients)
    {
        mIngredients = ingredients;
    }


}
