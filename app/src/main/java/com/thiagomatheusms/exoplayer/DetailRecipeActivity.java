package com.thiagomatheusms.exoplayer;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.thiagomatheusms.exoplayer.Fragments.IngredientsListFragment;
import com.thiagomatheusms.exoplayer.Fragments.StepListFragment;
import com.thiagomatheusms.exoplayer.Model.Step;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailRecipeActivity extends AppCompatActivity implements StepListFragment.OnStepClickListener {

    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);


        if (findViewById(R.id.teste) != null) {
            mTwoPane = true;

            IngredientsListFragment ingredientsListFragment = new IngredientsListFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.ingredients_container, ingredientsListFragment)
                    .commit();


        } else {
            mTwoPane = false;

            IngredientsListFragment ingredientsListFragment = new IngredientsListFragment();
            StepListFragment stepListFragment = new StepListFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.ingredients_container, ingredientsListFragment)
                    .commit();
            fragmentManager.beginTransaction()
                    .add(R.id.steps_container, stepListFragment)
                    .commit();

        }


    }

    @Override
    public void onStepSelected(int position, List<Step> stepList) {

        Intent intent = new Intent(this, DetailStepActivity.class);
        intent.putExtra("POSITION", position);
        intent.putParcelableArrayListExtra("STEPS", (ArrayList<Step>) stepList);

        startActivity(intent);
    }
}
