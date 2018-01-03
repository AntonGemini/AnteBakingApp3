package com.example.asassa.bakingapp3.Utils;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by sassa_000 on 02.01.2018.
 */

@AutoValue
public abstract class Ingredient implements Parcelable{
    abstract double quantity();
    abstract String measure();
    abstract String ingredient();

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

        /*public void setQuantity(double quantity)
        {
            this.quantity = quantity;
        }

        public double getQuantity()
        {
            return quantity;
        }


        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }*/
}
