package com.example.asassa.bakingapp3.Utils;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by sassa_000 on 02.01.2018.
 */

@AutoValue
public abstract class Step implements Parcelable{

    abstract int id();
    abstract String shortDescription();
    abstract String description();
    abstract String videoURL();
    abstract String thumbnailURL();


    @AutoValue.Builder
    abstract static class Builder
    {
        abstract Builder setId(int value);
        abstract Builder setShortDescription(String value);
        abstract Builder setDescription(String value);
        abstract Builder setVideoURL(String value);
        abstract Builder setThumbnailURL(String value);
        abstract Step build();
    }

    static Builder builder()
    {
        return new AutoValue_Step.Builder();
    }

}
