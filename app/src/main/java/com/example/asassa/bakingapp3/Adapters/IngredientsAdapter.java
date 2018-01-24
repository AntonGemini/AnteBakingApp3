package com.example.asassa.bakingapp3.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asassa.bakingapp3.R;
import com.example.asassa.bakingapp3.Utils.Ingredient;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by ASassa on 08.01.2018.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    List<Ingredient> mIngredients;
    Context mContext;

    public IngredientsAdapter(Context context, List<Ingredient> ingerdients)
    {
        mContext = context;
        mIngredients = ingerdients;

    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View rowView = layoutInflater.inflate(R.layout.step_item,parent,false);
        return new IngredientViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        holder.textView.setText(mContext.getString(R.string.ingredients_item,mIngredients.get(position).ingredient(),
                    mIngredients.get(position).quantity(),mIngredients.get(position).measure()));
    }

    @Override
    public int getItemCount() {
        if (mContext != null) return mIngredients.size();
        return 0;
    }


    class IngredientViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public IngredientViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_step_name);
        }
    }
}
