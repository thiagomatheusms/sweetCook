package com.thiagomatheusms.exoplayer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thiagomatheusms.exoplayer.Model.Recipe;
import com.thiagomatheusms.exoplayer.R;

import java.util.List;

public class MasterListAdapter extends RecyclerView.Adapter<MasterListAdapter.RecipesAdapterViewHolder> {

    private List<Recipe> mRecipes;
    private RecipesAdapterOnClickListener recipesAdapterOnClickListener;

    public MasterListAdapter(RecipesAdapterOnClickListener recipesAdapterOnClickListener, List<Recipe> recipesList){
        this.recipesAdapterOnClickListener = recipesAdapterOnClickListener;
        this.mRecipes = recipesList;
    }


    @NonNull
    @Override
    public RecipesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int idLayoutItemRecipeList = R.layout.item_recipe_list;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(idLayoutItemRecipeList, viewGroup, false);
        RecipesAdapterViewHolder recipesAdapterViewHolder = new RecipesAdapterViewHolder(view);

        return recipesAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesAdapterViewHolder recipesAdapterViewHolder, int i) {
        String recipeName = mRecipes.get(i).getName();
        recipesAdapterViewHolder.mRecipeName.setText(recipeName);

        switch (recipeName){

            case "Cheesecake":
                recipesAdapterViewHolder.mRecipeImage.setImageResource(R.drawable.cheesecake);
                break;

            case "Yellow Cake":
                recipesAdapterViewHolder.mRecipeImage.setImageResource(R.drawable.yellowcake);
                break;

            case "Brownies":
                recipesAdapterViewHolder.mRecipeImage.setImageResource(R.drawable.brownies);
                break;

            case "Nutella Pie":
                recipesAdapterViewHolder.mRecipeImage.setImageResource(R.drawable.nutellapie);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(mRecipes == null){
            return 0;
        }
        return mRecipes.size();
    }

    public interface RecipesAdapterOnClickListener{
        void onMyClickListener(Recipe recipe);
    }

    public class RecipesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView mRecipeImage;
        TextView mRecipeName;

        public RecipesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            mRecipeImage = (ImageView) itemView.findViewById(R.id.img_recipe);
            mRecipeName = (TextView) itemView.findViewById(R.id.tv_recipeName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Recipe recipe = mRecipes.get(position);
            recipesAdapterOnClickListener.onMyClickListener(recipe);
        }
    }



    public void setRecipeList(List<Recipe> recipeList){
        this.mRecipes = recipeList;
        notifyDataSetChanged();
    }
}
