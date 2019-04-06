package com.thiagomatheusms.exoplayer;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thiagomatheusms.exoplayer.Endpoints.GetDataService;
import com.thiagomatheusms.exoplayer.Model.Recipe;
import com.thiagomatheusms.exoplayer.Utilities.RetrofitClientInstance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestUtils {

    static List<Recipe> recipesList;
    static GetDataService service;

    public static void delay(int millis) {
        SystemClock.sleep(millis);
    }

    public static List<Recipe> getRecipes() {
        Log.i("LISTA", "ENTROU 1");

        recipesList = new ArrayList<>();
        loadRecipes();

        return recipesList;
    }


    private static void loadRecipes() {
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Recipe>> call = service.getRecipes();

        try {
            recipesList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("LISTA", "ENTROU 3");

    }

    public static void setRecipesList(List<Recipe> lista){
        Log.i("LISTA", "ENTROU 4");
        recipesList = lista;

    }


}