package com.thiagomatheusms.exoplayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.thiagomatheusms.exoplayer.Fragments.DetailStepFragment;
import com.thiagomatheusms.exoplayer.Model.Step;

import java.util.ArrayList;
import java.util.List;

public class DetailStepActivity extends AppCompatActivity {

    DetailStepFragment detailStepFragment;
    List<Step> teste = new ArrayList<>();
    int index;
    Button mButtonNext, mButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_step);

        detailStepFragment = new DetailStepFragment();

        Intent intent = getIntent();
        teste = intent.getParcelableArrayListExtra("STEPS");
        detailStepFragment.setmListStep(teste);


        if (savedInstanceState == null) {

            if (intent.hasExtra("POSITION") && intent.hasExtra("STEPS")) {
                index = intent.getIntExtra("POSITION", index);
                detailStepFragment.setPosition(index);

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.detail_step_container, detailStepFragment)
                        .commit();

            }

        } else {
            index = savedInstanceState.getInt("TESTE");
        }

        mButtonNext = (Button) findViewById(R.id.btn_next);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index < (detailStepFragment.getmListStep().size() - 1)) {
                    index++;
                    DetailStepFragment detailStepFragment1 = new DetailStepFragment();
                    detailStepFragment1.setmListStep(detailStepFragment.getmListStep());
                    detailStepFragment1.setPosition(index);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.detail_step_container, detailStepFragment1)
                            .commit();
                }
            }
        });

        mButtonBack = (Button) findViewById(R.id.btn_back);
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index > 0) {
                    index--;
                    DetailStepFragment detailStepFragment1 = new DetailStepFragment();
                    detailStepFragment1.setmListStep(detailStepFragment.getmListStep());
                    detailStepFragment1.setPosition(index);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.detail_step_container, detailStepFragment1)
                            .commit();
                }
            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("TESTE", index);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            mButtonNext.setVisibility(View.GONE);
            mButtonBack.setVisibility(View.GONE);

        } else {
            mButtonNext.setVisibility(View.VISIBLE);
            mButtonBack.setVisibility(View.VISIBLE);
        }
    }

//    @SuppressLint("InlinedApi")
//    private void hideSystemUiFullScreen() {
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//    }
//
//    @SuppressLint("InlinedApi")
//    private void hideSystemUi2() {
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//    }
}
