package com.thiagomatheusms.exoplayer.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thiagomatheusms.exoplayer.Adapter.StepListAdapter;
import com.thiagomatheusms.exoplayer.Model.Step;
import com.thiagomatheusms.exoplayer.R;

import java.util.ArrayList;
import java.util.List;

public class StepListFragment extends Fragment implements StepListAdapter.StepAdapterOnClickListener {

    private RecyclerView recyclerView;
    private StepListAdapter stepListAdapter;
    private OnStepClickListener mCallback;
    public static List<Step> mSteps;

    public interface OnStepClickListener {
        void onStepSelected(int position, List<Step> stepList);
    }

    public StepListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnStepClickListener) context;
        } catch (ClassCastException e) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_step_list, container, false);

        Intent intent = getActivity().getIntent();

        if (intent.hasExtra("STEPS")) {
            mSteps = (List<Step>) intent.getSerializableExtra("STEPS");
        }

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_steps);
        stepListAdapter = new StepListAdapter(this, mSteps);
        recyclerView.setAdapter(stepListAdapter);

        return rootView;
    }

    @Override
    public void onMyStepClickListener(int position, Step step) {
        mCallback.onStepSelected(position, mSteps);
    }

}
