package com.example.asassa.bakingapp3.Utils;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;

/**
 * Created by ASassa on 16.01.2018.
 */

public class IngredientsViewFactory implements RemoteViewsService.RemoteViewsFactory {

    List<String> mIngredients;
    Context mContext;

    public IngredientsViewFactory(Context context, List<String> ingredients)
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

    }

    @Override
    public int getCount() {
        return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(),android.R.layout.simple_list_item_1);
        rv.setTextViewText(android.R.id.text1,mIngredients.get(i));
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
