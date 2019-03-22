package com.thiagomatheusms.exoplayer.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;
import com.thiagomatheusms.exoplayer.DetailStepActivity;
import com.thiagomatheusms.exoplayer.Model.Step;
import com.thiagomatheusms.exoplayer.R;
import com.google.android.exoplayer2.*;
import com.google.android.exoplayer2.upstream.*;
import com.google.android.exoplayer2.source.*;

import java.util.ArrayList;
import java.util.List;


public class DetailStepFragment extends Fragment implements Player.EventListener {

    private PlayerView playerView;
    private TextView mStepDescription;

    private Uri uriVideo;
    private List<Step> mListStep;
    private int position;

    /*ExoPlayer's Variables*/
    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mListStep = savedInstanceState.getParcelableArrayList("STEPS");
            position = savedInstanceState.getInt("INDEX");
        }

        View rootView = inflater.inflate(R.layout.fragment_detail_step, container, false);

        mStepDescription = rootView.findViewById(R.id.tv_step_description);

        playerView = rootView.findViewById(R.id.playerView);
        playerView.requestFocus();

        if (mListStep != null) {

            if (!mListStep.get(position).getVideoURL().equals("")) {
                playerView.setVisibility(View.VISIBLE);
                uriVideo = Uri.parse(mListStep.get(position).getVideoURL());
            } else {
                playerView.setVisibility(View.GONE);
            }

            mStepDescription.setText(mListStep.get(position).getDescription());
        }


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer(uriVideo);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer(uriVideo);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    /*player's methods*/

    public void initializePlayer(Uri uriVideo) {
        player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()), new DefaultTrackSelector(), new DefaultLoadControl());
        playerView.setPlayer(player);

        MediaSource mediaSource = buildMediaSourve(uriVideo);

        player.prepare(mediaSource, true, false);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);


    }

    private MediaSource buildMediaSourve(Uri uri) {
        return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("exoplayer-codelab")).createMediaSource(uri);
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player.stop();
            player = null;
        }
    }


    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    /*getters and setters*/

    public List<Step> getmListStep() {
        return mListStep;
    }

    public void setmListStep(List<Step> mListStep) {
        this.mListStep = mListStep;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    /*interface's methods*/

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray
            trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    /*onSaveInstanceState*/

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("STEPS", (ArrayList<Step>) mListStep);
        outState.putInt("INDEX", position);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int currentOrientation = getResources().getConfiguration().orientation;
        int height = playerView.getHeight();

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//            hideSystemUiFullScreen();
            mStepDescription.setVisibility(View.GONE);
            playerView.getLayoutParams().height = WindowManager.LayoutParams.MATCH_PARENT;

        } else {
//            hideSystemUi2();
            mStepDescription.setVisibility(View.VISIBLE);
            playerView.getLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;

        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUiFullScreen() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi2() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}
