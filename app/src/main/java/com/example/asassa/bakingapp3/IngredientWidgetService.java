package com.example.asassa.bakingapp3;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.asassa.bakingapp3.Utils.IngredientsViewFactory;

import java.util.ArrayList;


/**
 * Created by ASassa on 15.01.2018.
 */

public class IngredientWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        ArrayList<String> ingredients = intent.getExtras().getStringArrayList(getBaseContext().getString(R.string.ingredient_lst));
        return new IngredientsViewFactory(getBaseContext(),ingredients);

    }
}
