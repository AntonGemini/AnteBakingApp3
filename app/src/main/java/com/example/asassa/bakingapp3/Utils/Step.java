package com.example.asassa.bakingapp3.Utils;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by sassa_000 on 02.01.2018.
 */

@AutoValue
public abstract class Step implements Parcelable{

    public abstract int id();
    public abstract String shortDescription();
    public abstract String description();
    public abstract String videoURL();
    public abstract String thumbnailURL();


    public static Step create(int id, String shortDescription, String description, String videoURL, String thumbnailURL)
    {
        return new AutoValue_Step(id, shortDescription, description, videoURL, thumbnailURL);
    }


    @AutoValue.Builder
    public abstract static class Builder
    {
        abstract Builder setId(int value);
        abstract Builder setShortDescription(String value);
        abstract Builder setDescription(String value);
        abstract Builder setVideoURL(String value);
        abstract Builder setThumbnailURL(String value);
        abstract Step build();
    }

    public static Builder builder()
    {
        return new AutoValue_Step.Builder();
    }

    public static TypeAdapter<Step> typeAdapter(Gson gson) {
        return new AutoValue_Step.GsonTypeAdapter(gson);
    }

}
