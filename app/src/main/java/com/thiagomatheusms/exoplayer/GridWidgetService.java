package com.thiagomatheusms.exoplayer;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import com.thiagomatheusms.exoplayer.Fragments.IngredientsListFragment;
import com.thiagomatheusms.exoplayer.Model.Ingredient;
import com.thiagomatheusms.exoplayer.Model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class GridWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewFactory(this.getApplicationContext());
    }
}

class GridRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;
    List<Ingredient> mIngredients = new ArrayList<>();
    Recipe recipe;

    public GridRemoteViewFactory(Context context) {
        this.mContext = context;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        mIngredients = IngredientsListFragment.mIngredients;
        recipe = IngredientsListFragment.recipe;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (mIngredients == null) {
            return 0;
        }

        return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        if (mIngredients == null || mIngredients.size() == 0) {
            return null;
        }

        String mIngredient = mIngredients.get(i).getQuantity() + " " + mIngredients.get(i).getMeasure() + " - " + mIngredients.get(i).getIngredientName();

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.recipe_widget);
        views.setTextViewText(R.id.appwidget_text, mIngredient);

        Bundle extras = new Bundle();
        extras.putString("teste", recipe.getName());
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        views.setOnClickFillInIntent(R.id.appwidget_text, fillInIntent);


//        Intent fillIntent = new Intent();
//        views.setOnClickFillInIntent(R.id.appwidget_text, fillIntent);


        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
