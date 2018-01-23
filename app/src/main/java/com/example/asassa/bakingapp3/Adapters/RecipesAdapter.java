package com.example.asassa.bakingapp3.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asassa.bakingapp3.R;
import com.example.asassa.bakingapp3.Utils.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sassa_000 on 30.12.2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesHolder> {

    Context mContext;
    List<Recipe> mRecipeList = null;


    public interface OnRecipeClickListener
    {
        void onRecipeClick(int recipeId);
    }

    private OnRecipeClickListener recipeClickListener;

    public RecipesAdapter(Context context, List<Recipe> recipeList){
        mContext = context;
        mRecipeList = recipeList;
        recipeClickListener = (OnRecipeClickListener)context;
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
        holder.servingsNameText.setText(mContext.getString(R.string.servings_text,recipe.servings()));
        String imgSrc = recipe.image();
        if (!imgSrc.equals(""))
        {
            Picasso.with(mContext).load(imgSrc).into(holder.recipeImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public class RecipesHolder extends RecyclerView.ViewHolder {
        public TextView recipeNameText;
        public TextView servingsNameText;
        public ImageView recipeImageView;

        public RecipesHolder(View itemView) {
            super(itemView);
            recipeNameText = itemView.findViewById(R.id.recipe_name);
            servingsNameText = itemView.findViewById(R.id.num_servings);
            recipeImageView = itemView.findViewById(R.id.iv_recipe);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    recipeClickListener.onRecipeClick(position);
                }
            });
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
