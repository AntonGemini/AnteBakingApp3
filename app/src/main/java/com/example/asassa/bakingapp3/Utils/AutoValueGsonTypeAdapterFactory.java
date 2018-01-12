package com.example.asassa.bakingapp3.Utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by sassa_000 on 12.01.2018.
 */

@GsonTypeAdapterFactory
public abstract class AutoValueGsonTypeAdapterFactory implements TypeAdapterFactory {


    public static TypeAdapterFactory create() {
        return new AutoValueGson_AutoValueGsonTypeAdapterFactory();
    }
}
