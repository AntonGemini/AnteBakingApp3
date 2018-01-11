package com.example.asassa.bakingapp3.Utils;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by sassa_000 on 02.01.2018.
 */

@AutoValue
public abstract class Ingredient implements Parcelable{
    public abstract double quantity();
    public abstract String measure();
    public abstract String ingredient();

    public static Ingredient create(double quantity, String measure, String ingredient)
    {
        return new AutoValue_Ingredient(quantity,measure,ingredient);
    }

    @AutoValue.Builder
    abstract static class Builder{
        abstract Builder setQuantity(double value);
        abstract Builder setMeasure(String value);
        abstract Builder setIngredient(String value);
        abstract Ingredient build();
    }

    static Builder builder()
    {
        return new AutoValue_Ingredient.Builder();
    }

    public static TypeAdapter<Ingredient> typeAdapter(Gson gson)
    {
        return new AutoValue_Ingredient.GsonTypeAdapter(gson);
    }

}
