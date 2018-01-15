package com.example.asassa.bakingapp3;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;
import java.util.Set;

/**
 * Created by ASassa on 15.01.2018.
 */

public class IngredientWidgetService extends RemoteViewsService {


    List<String> mIngredients;

    @Override
    public RemoteViewsFactory onGetViewFactory(final Intent intent) {
        String st = "122";

        return new RemoteViewsFactory() {

            @Override
            public void onCreate() {
                try {
                    mIngredients = intent.getExtras().getStringArrayList("lst");
                }
                catch (Exception ex)
                {
                    mIngredients = null;
                }
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
                RemoteViews rv = new RemoteViews(getBaseContext().getPackageName(),android.R.layout.simple_list_item_1);
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
        };
    }
}
