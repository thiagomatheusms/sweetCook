package com.thiagomatheusms.exoplayer;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thiagomatheusms.exoplayer.Fragments.DetailStepFragment;
import com.thiagomatheusms.exoplayer.Model.Step;

public class DetailStepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_step);

        final DetailStepFragment detailStepFragment = new DetailStepFragment();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.detail_step_container, detailStepFragment)
                .commit();


        Button mButtonNext = (Button) findViewById(R.id.btn_next);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailStepFragment.nextVideo();
            }
        });

        Button mButtonBack = (Button) findViewById(R.id.btn_back);
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailStepFragment.backVideo();
            }
        });

    }
}
