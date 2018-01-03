package com.example.asassa.bakingapp3.Utils;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * Created by ASassa on 25.12.2017.
 */

@AutoValue
public abstract class Recipe implements Parcelable {

    abstract int id();
    abstract String name();
    abstract List<Ingredient> ingredients();
    abstract List<Step> steps();
    abstract int servings();
    abstract String image();

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

}






