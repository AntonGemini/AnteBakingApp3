package com.example.asassa.bakingapp3.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asassa.bakingapp3.R;

import java.util.List;

/**
 * Created by sassa_000 on 30.12.2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesHolder> {

    Context mContext;
    List<Recipe> mRecipeList = null;

    public RecipesAdapter(Context context, List<Recipe> recipeList){
        mContext = context;
        mRecipeList = recipeList;
    }

    @Override
    public RecipesAdapter.RecipesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View cardView = layoutInflater.inflate(R.layout.recipe_card,parent,false);
        return new RecipesHolder(cardView);
    }

    @Override
    public void onBindViewHolder(RecipesAdapter.RecipesHolder holder, int position) {
        Recipe recipe = mRecipeList.get(position);
        holder.recipeNameText.setText(recipe.name());
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public class RecipesHolder extends RecyclerView.ViewHolder {
        public TextView recipeNameText;
        public RecipesHolder(View itemView) {
            super(itemView);
            recipeNameText = itemView.findViewById(R.id.recipe_name);
        }
    }

    public void reloadData(List<Recipe> list)
    {
        if (list != null)
        {
            mRecipeList.clear();
            mRecipeList.addAll(list);
            notifyDataSetChanged();
        }

    }



}
