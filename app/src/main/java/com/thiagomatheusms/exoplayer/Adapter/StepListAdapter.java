package com.thiagomatheusms.exoplayer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thiagomatheusms.exoplayer.Model.Step;
import com.thiagomatheusms.exoplayer.R;

import java.util.List;

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepListAdapterViewHolder> {

    private List<Step> mSteps;
    private StepAdapterOnClickListener mStepAdapterOnClickListener;

    public StepListAdapter(StepAdapterOnClickListener stepAdapterOnClickListener ,List<Step> mSteps) {
        this.mStepAdapterOnClickListener = stepAdapterOnClickListener;
        this.mSteps = mSteps;
    }

    @NonNull
    @Override
    public StepListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int idLayoutItemStepList = R.layout.item_step_list;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(idLayoutItemStepList, viewGroup, false);
        StepListAdapterViewHolder stepListAdapterViewHolder = new StepListAdapterViewHolder(view);

        return stepListAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepListAdapterViewHolder stepListAdapterViewHolder, int i) {
        String stepDescription = mSteps.get(i).getShortDescription();

        stepListAdapterViewHolder.mStepDescription.setText(stepDescription);
    }

    @Override
    public int getItemCount() {
        if(mSteps == null) {
            return 0;
        }

        return mSteps.size();
    }

    public interface StepAdapterOnClickListener{
        void onMyStepClickListener(int position, Step step);
    }

    public class StepListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mStepDescription;

        public StepListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            mStepDescription = (TextView) itemView.findViewById(R.id.tv_step_shortDescription);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Step step = mSteps.get(position);
            mStepAdapterOnClickListener.onMyStepClickListener(position, step);
        }
    }
}
