package com.thiagomatheusms.exoplayer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thiagomatheusms.exoplayer.Model.Ingredient;
import com.thiagomatheusms.exoplayer.R;

import java.util.List;

public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.IngredientsListAdapterViewHolder>  {

    private List<Ingredient> mIngredients;

    public IngredientsListAdapter(List<Ingredient> listIngredients){
        this.mIngredients = listIngredients;
    }


    @NonNull
    @Override
    public IngredientsListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int idLayoutItemIngredientList = R.layout.item_ingredient_list;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(idLayoutItemIngredientList, viewGroup, false);
        IngredientsListAdapterViewHolder ingredientsListAdapterViewHolder = new IngredientsListAdapterViewHolder(view);

        return ingredientsListAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsListAdapterViewHolder ingredientsListAdapterViewHolder, int i) {
        float quantity = mIngredients.get(i).getQuantity();
        String measure = mIngredients.get(i).getMeasure();
        String ingredient = mIngredients.get(i).getIngredientName();

        ingredientsListAdapterViewHolder.mQuantity.setText(String.valueOf(quantity));
        ingredientsListAdapterViewHolder.mMeasure.setText(measure);
        ingredientsListAdapterViewHolder.mIngredientName.setText(ingredient);

    }

    @Override
    public int getItemCount() {
        if(mIngredients == null) {
            return 0;
        }

        return mIngredients.size();
    }

    public class IngredientsListAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView mIngredientName, mQuantity, mMeasure;

        public IngredientsListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            mIngredientName = (TextView) itemView.findViewById(R.id.tv_igredientName);
            mQuantity = (TextView) itemView.findViewById(R.id.tv_quantity);
            mMeasure = (TextView) itemView.findViewById(R.id.tv_measure);
        }
    }

    public void setIngredientsList(List<Ingredient> ingredients){
        this.mIngredients = ingredients;
        notifyDataSetChanged();
    }
}
