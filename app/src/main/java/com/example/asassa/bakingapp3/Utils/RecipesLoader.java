package com.example.asassa.bakingapp3.Utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.asassa.bakingapp3.R;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASassa on 12.01.2018.
 */

public class RecipesLoader extends AsyncTaskLoader<List<Recipe>> {

    private Context mContext;
    private List<Recipe> mResult;

    public RecipesLoader(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mResult!=null)
        {
            deliverResult(mResult);
        }
        else {
            forceLoad();
        }
    }

    @Override
    public List<Recipe> loadInBackground() {
        //return NetworkProvider.deSerialize(mContext.getString(R.string.recipes_json),new TypeToken<ArrayList<Recipe>>() {}.getType());
        return NetworkProvider.getRecipesJSON(mContext.getString(R.string.recipes_json));
    }

    @Override
    public void deliverResult(List<Recipe> data) {
        mResult = data;
        super.deliverResult(data);
    }
}
