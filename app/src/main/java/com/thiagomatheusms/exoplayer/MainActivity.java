package com.thiagomatheusms.exoplayer;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.thiagomatheusms.exoplayer.Adapter.MasterListAdapter;
import com.thiagomatheusms.exoplayer.Endpoints.GetDataService;
import com.thiagomatheusms.exoplayer.Fragments.MasterListFragment;
import com.thiagomatheusms.exoplayer.Model.Recipe;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnRecipeClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onRecipeSelected(Recipe recipe) {
        Toast.makeText(this, recipe.getName(), Toast.LENGTH_SHORT).show();

    }
}
