package com.example.asassa.bakingapp3.Adapters;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asassa.bakingapp3.R;
import com.example.asassa.bakingapp3.Utils.Ingredient;

import java.util.List;

/**
 * Created by ASassa on 08.01.2018.
 */

public class IngredientsAdapter extends BaseAdapter {

    List<Ingredient> mIngredients;
    Context mContext;

    public IngredientsAdapter(Context context, List<Ingredient> ingerdients)
    {
        mContext = context;
        mIngredients = ingerdients;

    }


    @Override
    public int getCount() {
        if (mContext != null) return mIngredients.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.step_item,viewGroup,false);
        TextView textView = rowView.findViewById(R.id.tv_step_name);
        if (view == null)
        {
            textView.setText(mIngredients.get(i).ingredient()+"\n");
            textView.append(mIngredients.get(i).quantity()+" ");
            textView.append(mIngredients.get(i).measure());
        }
        else
        {
            rowView = view;
        }
        return rowView;
    }
}
