package com.example.asassa.bakingapp3;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.RemoteViews;

import com.example.asassa.bakingapp3.Utils.AutoValueGsonTypeAdapterFactory;
import com.example.asassa.bakingapp3.Utils.Ingredient;
import com.example.asassa.bakingapp3.Utils.Recipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link IngredientsWidgetConfigureActivity IngredientsWidgetConfigureActivity}
 */
public class IngredientsWidget extends AppWidgetProvider {

    public static int cnt = 0;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //CharSequence widgetText = IngredientsWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonTypeAdapterFactory.create()).create();
        String recipeJson = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(IngredientsWidgetConfigureActivity.PREF_PREFIX_KEY+" "+appWidgetId,null);
        Recipe recipe = gson.fromJson(recipeJson,Recipe.class);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget);

        //views.setTextViewText(R.id.appwidget_text, widgetText);


        if (recipe != null) {
//
            ArrayList<String> ings = new ArrayList<>();
            for(Ingredient ing : recipe.ingredients())
            {
                ings.add(ing.toString());
            }

            Intent intent = new Intent(context, IngredientWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
            intent.setType(String.valueOf(cnt));
            intent.putStringArrayListExtra("lst",ings);

            intent.putExtra("random",cnt);
            views.setTextViewText(R.id.appwidget_text,recipe.name());
            views.setRemoteAdapter(R.id.lv_widget_ingredients,intent);

            Intent recipeIntent = new Intent(context,StepsActivity.class);
            recipeIntent.putExtra("recipe",recipe);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,cnt,recipeIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.lv_widget_ingredients,pendingIntent);
            cnt++;
        }

        // Instruct the widget manager to update the widget
        //appWidgetManager.updateAppWidget(appWidgetId, null);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            IngredientsWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

