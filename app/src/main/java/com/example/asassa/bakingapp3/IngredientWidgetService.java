package com.example.asassa.bakingapp3;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.asassa.bakingapp3.Utils.IngredientsViewFactory;

import java.util.List;
import java.util.Set;

/**
 * Created by ASassa on 15.01.2018.
 */

public class IngredientWidgetService extends RemoteViewsService {



    @Override
    public RemoteViewsFactory onGetViewFactory(final Intent intent) {

        return new IngredientsViewFactory(getBaseContext(),intent.getExtras().getStringArrayList("lst"));
    }
}
