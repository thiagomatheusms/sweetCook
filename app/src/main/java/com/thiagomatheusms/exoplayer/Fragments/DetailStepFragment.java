package com.thiagomatheusms.exoplayer.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;
import com.thiagomatheusms.exoplayer.Model.Step;
import com.thiagomatheusms.exoplayer.R;
import com.google.android.exoplayer2.*;
import com.google.android.exoplayer2.upstream.*;
import com.google.android.exoplayer2.source.*;

import java.util.List;


public class DetailStepFragment extends Fragment implements Player.EventListener {

    private SimpleExoPlayer exoPlayer;
    private PlayerView playerView;
    private TextView mStepDescription;

    private Uri uriVideo;
    public List<Step> mListStep;
    public int position;


    public DetailStepFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail_step, container, false);

        Intent intent = getActivity().getIntent();
        if (intent.hasExtra("POSITION") && intent.hasExtra("STEPS")) {
            position = intent.getIntExtra("POSITION", position);
            mListStep = intent.getParcelableArrayListExtra("STEPS");

            mStepDescription = rootView.findViewById(R.id.tv_step_description);
            mStepDescription.setText(mListStep.get(position).getDescription());

            uriVideo = Uri.parse(mListStep.get(position).getVideoURL());

            playerView = rootView.findViewById(R.id.playerView);
            playerView.requestFocus();
        }

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initializePlayer(uriVideo);
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

    public void initializePlayer(Uri mediaUri) {
        if (exoPlayer == null) {

            exoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity().getBaseContext(), new DefaultTrackSelector());
            exoPlayer.addListener(this);
            playerView.setPlayer(exoPlayer);

            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getActivity().getBaseContext(), Util.getUserAgent(getActivity().getBaseContext(), "ExoPlayer"));

            ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mediaUri);

            exoPlayer.prepare(extractorMediaSource);
            exoPlayer.setPlayWhenReady(true);


            Toast.makeText(getActivity().getBaseContext(), " Position: " + position + "", Toast.LENGTH_SHORT).show();

        }
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    public void nextVideo() {
        if (position < (mListStep.size() - 1)) {
            releasePlayer();
            this.position++;

            if (!mListStep.get(position).getVideoURL().equals("")) {
                playerView.setVisibility(View.VISIBLE);
                initializePlayer(Uri.parse(mListStep.get(position).getVideoURL()));
            }else{
                playerView.setVisibility(View.GONE);
            }
            mStepDescription.setText(mListStep.get(position).getDescription());

            Toast.makeText(getActivity().getBaseContext(), mListStep.size() + " Position: " + position + "", Toast.LENGTH_SHORT).show();

        }
    }

    public void backVideo() {
        Toast.makeText(getActivity().getBaseContext(), mListStep.size() + " Position: " + position + "", Toast.LENGTH_SHORT).show();
        if (position > 0) {
            releasePlayer();
            this.position--;
            initializePlayer(Uri.parse(mListStep.get(position).getVideoURL()));
            mStepDescription.setText(mListStep.get(position).getDescription());
        }
    }


    /*Getter and Setter*/
    public Uri getUriVideo() {
        return uriVideo;
    }

    public void setUriVideo(Uri uriVideo) {
        this.uriVideo = uriVideo;
    }

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
        int status = exoPlayer.getPlaybackState();

        if (playWhenReady && (playbackState == Player.STATE_IDLE)) {
        }

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
}
