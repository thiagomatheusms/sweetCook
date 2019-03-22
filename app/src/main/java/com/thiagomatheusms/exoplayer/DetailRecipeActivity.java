package com.thiagomatheusms.exoplayer;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.thiagomatheusms.exoplayer.Fragments.DetailStepFragment;
import com.thiagomatheusms.exoplayer.Fragments.IngredientsListFragment;
import com.thiagomatheusms.exoplayer.Fragments.StepListFragment;
import com.thiagomatheusms.exoplayer.Model.Step;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailRecipeActivity extends AppCompatActivity implements StepListFragment.OnStepClickListener {

    private boolean mTwoPane;
    private IngredientsListFragment ingredientsListFragment;
    private DetailStepFragment detailStepFragment;
    private int position;
    private List<Step> mSteps;
    private View stepFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);

        //If it's a tablet.
        if (findViewById(R.id.step_detail_container) != null) {
            mTwoPane = true;

            ingredientsListFragment = new IngredientsListFragment();
            detailStepFragment = new DetailStepFragment();

            //The static fragment with the all steps on the left.
            stepFragments = findViewById(R.id.steps_container);

            if (savedInstanceState != null) {
                mSteps = savedInstanceState.getParcelableArrayList("STEPS");
                position = savedInstanceState.getInt("POSITION");

                detailStepFragment.setmListStep(mSteps);
                detailStepFragment.setPosition(position);
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.ingredients_container, ingredientsListFragment)
                    .commit();
            fragmentManager.beginTransaction()
                    .add(R.id.step_detail_container, detailStepFragment)
                    .commit();

        //Cellphone
        } else {
            mTwoPane = false;

            IngredientsListFragment ingredientsListFragment = new IngredientsListFragment();
            StepListFragment stepListFragment = new StepListFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.ingredients_container, ingredientsListFragment)
                    .commit();
            fragmentManager.beginTransaction()
                    .replace(R.id.steps_container, stepListFragment)
                    .commit();

        }


    }

    @Override
    public void onStepSelected(int position, List<Step> stepList) {
        mSteps = stepList;
        this.position = position;

        /*If it's a tablet*/
        if (mTwoPane) {

            DetailStepFragment detailStepFragment1 = new DetailStepFragment();
            detailStepFragment1.setmListStep(stepList);
            detailStepFragment1.setPosition(position);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_detail_container, detailStepFragment1)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailStepActivity.class);
            intent.putExtra("POSITION", position);
            intent.putParcelableArrayListExtra("STEPS", (ArrayList<Step>) stepList);

            startActivity(intent);

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("STEPS", (ArrayList<Step>) mSteps);
        outState.putInt("POSITION", position);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            ingredientsListFragment.getView().setVisibility(View.GONE);
            stepFragments.setVisibility(View.GONE);

        } else {
            ingredientsListFragment.getView().setVisibility(View.VISIBLE);
            stepFragments.setVisibility(View.VISIBLE);
        }
    }
}
