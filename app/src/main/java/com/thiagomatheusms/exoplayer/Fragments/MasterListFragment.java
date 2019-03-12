package com.thiagomatheusms.exoplayer.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thiagomatheusms.exoplayer.Adapter.MasterListAdapter;
import com.thiagomatheusms.exoplayer.MainActivity;
import com.thiagomatheusms.exoplayer.Model.Recipe;
import com.thiagomatheusms.exoplayer.R;
import com.thiagomatheusms.exoplayer.Utilities.MainViewModel;

import java.util.List;

public class MasterListFragment extends Fragment implements MasterListAdapter.RecipesAdapterOnClickListener {

    private RecyclerView recyclerView;
    private MasterListAdapter mAdapter;
    private OnRecipeClickListener mCallback;


    public interface OnRecipeClickListener {
        void onRecipeSelected(Recipe recipe);
    }

    /*This methed is call when the host activity initialize*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        /*This makes sure that the host Activity (main in this case) has implemented the callback interface*/
        try {
            mCallback = (OnRecipeClickListener) context;
        } catch (ClassCastException e) {

        }
    }

    /*Mandatory constructor for initialize the fragment*/
    public MasterListFragment() {
    }

    /*Inflates the fragment layout and sets the views*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_recipes);
        getAllRecipes();

        return rootView;
    }

    public void getAllRecipes() {

        MainViewModel viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        viewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                mAdapter = new MasterListAdapter(MasterListFragment.this, recipes);
                recyclerView.setAdapter(mAdapter);
            }
        });

    }

    /*Click RecyclerView*/
    @Override
    public void onMyClickListener(Recipe recipe) {
        mCallback.onRecipeSelected(recipe);
    }

}
