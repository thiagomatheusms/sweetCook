package com.thiagomatheusms.exoplayer.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thiagomatheusms.exoplayer.Adapter.IngredientsListAdapter;
import com.thiagomatheusms.exoplayer.MainActivity;
import com.thiagomatheusms.exoplayer.Model.Ingredient;
import com.thiagomatheusms.exoplayer.Model.Recipe;
import com.thiagomatheusms.exoplayer.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private IngredientsListAdapter ingredientsListAdapter;
    List<Ingredient> mIngredients;
    View rootView;

    public IngredientsListFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_ingredients_list, container, false);

        Intent intent = getActivity().getIntent();
        if (intent.hasExtra("INGREDIENTS") && intent.hasExtra("RECIPE")) {
            mIngredients = (List<Ingredient>) intent.getSerializableExtra("INGREDIENTS");
            Recipe recipe = intent.getParcelableExtra("RECIPE");
        }

//        getActivity().setTitle(recipe.getName());


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_ingredients);
        ingredientsListAdapter = new IngredientsListAdapter(mIngredients);
        recyclerView.setAdapter(ingredientsListAdapter);


        return rootView;
    }

    public void setIngredients(List<Ingredient> mIngredients) {
        this.mIngredients = new ArrayList<>();
        this.mIngredients.addAll(mIngredients);
    }
}
