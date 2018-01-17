package com.example.asassa.bakingapp3;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.asassa.bakingapp3.Utils.Ingredient;
import com.example.asassa.bakingapp3.Utils.IngredientsViewFactory;

import java.util.ArrayList;


/**
 * Created by ASassa on 15.01.2018.
 */

public class IngredientWidgetService extends RemoteViewsService {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        ArrayList<String> ingredients = intent.getExtras().getStringArrayList("lst");
        return new IngredientsViewFactory(getBaseContext(),ingredients);

    }
}
