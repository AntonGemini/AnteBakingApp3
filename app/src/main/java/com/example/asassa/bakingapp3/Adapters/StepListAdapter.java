package com.example.asassa.bakingapp3.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asassa.bakingapp3.Utils.Step;

import java.util.List;

/**
 * Created by ASassa on 03.01.2018.
 */

public class StepListAdapter extends BaseAdapter {

    List<Step> mSteps = null;
    Context mContext;

    public StepListAdapter(List<Step> steps)
    {
        mSteps = steps;
    }

    @Override
    public int getCount() {
        if (mSteps != null) return mSteps.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        TextView textView;
        if (view == null)
        {
            textView = new TextView(mContext);
            Step step = mSteps.get(i);
            

        }

        return null;
    }
}
