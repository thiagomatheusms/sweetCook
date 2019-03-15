package com.thiagomatheusms.exoplayer;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.thiagomatheusms.exoplayer.Adapter.MasterListAdapter;
import com.thiagomatheusms.exoplayer.Endpoints.GetDataService;
import com.thiagomatheusms.exoplayer.Fragments.IngredientsListFragment;
import com.thiagomatheusms.exoplayer.Fragments.MasterListFragment;
import com.thiagomatheusms.exoplayer.Fragments.StepListFragment;
import com.thiagomatheusms.exoplayer.Model.Ingredient;
import com.thiagomatheusms.exoplayer.Model.Recipe;
import com.thiagomatheusms.exoplayer.Model.Step;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnRecipeClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onRecipeSelected(Recipe recipe) {

        /*If has two panels (tablet)*/

        Toast.makeText(this, recipe.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetailRecipeActivity.class);
        intent.putParcelableArrayListExtra("INGREDIENTS", (ArrayList<Ingredient>) recipe.getIngredients());
        intent.putParcelableArrayListExtra("STEPS", (ArrayList<Step>) recipe.getSteps());
        intent.putExtra("RECIPE", recipe);
        startActivity(intent);

    }
}
