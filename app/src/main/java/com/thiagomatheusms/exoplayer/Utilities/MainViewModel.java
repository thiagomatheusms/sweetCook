package com.thiagomatheusms.exoplayer.Utilities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.thiagomatheusms.exoplayer.Endpoints.GetDataService;
import com.thiagomatheusms.exoplayer.Model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Recipe>> recipesList;
    private GetDataService service;

    public LiveData<List<Recipe>> getRecipes(){
        if(recipesList == null){
            recipesList = new MutableLiveData<List<Recipe>>();
            loadRecipes();
        }

        return recipesList;
    }

    private void loadRecipes(){
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Recipe>> call = service.getRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
               recipesList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.i("ERROR FALSE - ", t.getMessage());
            }
        });
    }

}
