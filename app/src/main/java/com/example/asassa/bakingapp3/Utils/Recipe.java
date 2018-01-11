package com.example.asassa.bakingapp3.Utils;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ASassa on 25.12.2017.
 */

@AutoValue
public abstract class Recipe implements Parcelable {

    @SerializedName("id") public abstract int id();
    @SerializedName("name") public abstract String name();
    public abstract List<Ingredient> ingredients();
    public abstract List<Step> steps();
    public abstract int servings();
    public abstract String image();

    public static Recipe create(int id, String name, List<Ingredient> ingredients, List<Step> steps, int servings, String image)
    {
        return new AutoValue_Recipe(id,name,ingredients,steps,servings,image);
    }

    @AutoValue.Builder
    abstract static class Builder
    {
        abstract Builder setId(int value);
        abstract Builder setName(String name);
        abstract Builder setIngredients(List<Ingredient> value);
        abstract Builder setSteps(List<Step> value);
        abstract Builder setServings(int value);
        abstract Builder setImage(String image);
        abstract Recipe build();

    }

    static Builder builder()
    {
        return new AutoValue_Recipe.Builder();
    }

    public static TypeAdapter<Recipe> typeAdapter(Gson gson)
    {
        return new AutoValue_Recipe.GsonTypeAdapter(gson);
    }

}






