package com.example.asassa.bakingapp3.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asassa.bakingapp3.R;
import com.example.asassa.bakingapp3.Utils.Recipe;

import java.util.List;

/**
 * Created by sassa_000 on 14.01.2018.
 */

public class WidgetRecyclerAdapter extends RecyclerView.Adapter<WidgetRecyclerAdapter.RecipeViewHolder> {

    private Context mContext;
    List<Recipe> mRecipes;

    public interface OnWidgetRecipeClick
    {
        void OnRecipeClick(Recipe recipe);
    }

    private OnWidgetRecipeClick onWidgetRecipeClick;

    public WidgetRecyclerAdapter(Context context, List<Recipe> recipes, OnWidgetRecipeClick onClickListener)
    {
        mContext = context;
        mRecipes = recipes;
        onWidgetRecipeClick = onClickListener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.widget_configure_row,parent,false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.recipeText.setText(mRecipes.get(position).name());
        String st = mRecipes.get(position).name();
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder
    {
        TextView recipeText;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeText = itemView.findViewById(R.id.tv_recipe_widget);
            recipeText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onWidgetRecipeClick.OnRecipeClick(mRecipes.get(getAdapterPosition()));
                }
            });
        }
    }
}


