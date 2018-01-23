package com.example.asassa.bakingapp3.Utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.asassa.bakingapp3.R;

import java.util.List;

/**
 * Created by ASassa on 12.01.2018.
 */

public class RecipesLoader extends AsyncTaskLoader<List<Recipe>> {

    private Context mContext;

    public RecipesLoader(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Recipe> loadInBackground() {
        return NetworkProvider.getRecipesJSON(mContext.getString(R.string.recipes_json));
    }
}
