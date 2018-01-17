package com.example.asassa.bakingapp3.Utils;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.asassa.bakingapp3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASassa on 16.01.2018.
 */

public class IngredientsViewFactory implements RemoteViewsService.RemoteViewsFactory {

    ArrayList<String> mIngredients;
    Context mContext;

    public IngredientsViewFactory(Context context, ArrayList<String> ingredients)
    {
        mContext = context;
        mIngredients = ingredients;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {
        mIngredients.clear();
    }

    @Override
    public int getCount() {
        return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(),R.layout.widget_recyclerview_row);
        rv.setTextViewText(R.id.tv_recipe_widget,mIngredients.get(i));
        Intent fillInIntent = new Intent();
        rv.setOnClickFillInIntent(R.id.tv_recipe_widget,fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
