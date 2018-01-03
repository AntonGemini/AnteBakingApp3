package com.example.asassa.bakingapp3.Utils;

import android.os.Parcelable;
import com.google.auto.value.AutoValue;

/**
 * Created by sassa_000 on 02.01.2018.
 */

@AutoValue
public abstract class Task implements Parcelable {

    abstract long id();
    abstract String summary();

    public static Task create(long id, String summary)
    {
        return new AutoValue_Task(id,summary);
    }

    @AutoValue.Builder
    abstract static class Builder
    {
        abstract Builder setId(long value);
        abstract Builder setSummary(String summary);
        abstract Task buiild();
    }

    static Builder builder()
    {
        return new AutoValue_Task.Builder();
    }
}
