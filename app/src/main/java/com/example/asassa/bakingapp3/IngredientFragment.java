package com.example.asassa.bakingapp3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by ASassa on 08.01.2018.
 */

public class IngredientFragment extends Fragment {

    ListView ingedientsView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient,container);
        ingedientsView = view.findViewById(R.id.lv_ingredients);

        //ingedientsView.setAdapter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
