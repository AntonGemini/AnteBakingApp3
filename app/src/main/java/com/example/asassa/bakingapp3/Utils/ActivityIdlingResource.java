package com.example.asassa.bakingapp3.Utils;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by sassa_000 on 19.01.2018.
 */

public class ActivityIdlingResource implements IdlingResource {

    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    @Nullable private volatile ResourceCallback mCallback;

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }

    public void setIdleState(boolean state)
    {
        mIsIdleNow.set(state);
        if (mCallback != null && mIsIdleNow.get())
        {
            mCallback.onTransitionToIdle();
        }
    }
}
