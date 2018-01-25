package com.example.asassa.bakingapp3;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asassa.bakingapp3.Utils.Step;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by ASassa on 05.01.2018.
 */

public class DetailsRecipeFragment extends Fragment {

    private Step mStep = null;
    private TextView tv;
    private SimpleExoPlayerView exoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private ImageView defaultImageView;
    private long savedPlayerPosition = 0;
    private boolean mPlayerState = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_recipe,container,false);
        tv = view.findViewById(R.id.tv_step_description);
        exoPlayerView = view.findViewById(R.id.exo_details_video);
        defaultImageView = view.findViewById(R.id.iv_default_step);

        Intent intent = getActivity().getIntent();
        if (savedInstanceState != null)
        {
            mStep = savedInstanceState.getParcelable(getString(R.string.step_parcel));
            savedPlayerPosition = savedInstanceState.getLong(getString(R.string.last_player_position));
            mPlayerState = savedInstanceState.getBoolean(getString(R.string.player_state));

        }
        else if (intent != null && mStep == null) {
            mStep = intent.getExtras().getParcelable(getString(R.string.step_parcel));
        }
        String ts = mStep.description();
        tv.setText(ts);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getString(R.string.step_parcel), mStep);
        if (mExoPlayer!= null) {
            savedPlayerPosition = mExoPlayer.getCurrentPosition();
            outState.putLong(getString(R.string.last_player_position), savedPlayerPosition);
            outState.putBoolean(getString(R.string.player_state),mPlayerState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(mStep.videoURL())) {
            intializePlayer(mStep.videoURL());
        }
        else
        {
            setDefaultImage(mStep.thumbnailURL());
        }
    }

    public void setStepDetails(Step step)
    {
        mStep = step;
    }

    private void intializePlayer(String videoUrl)
    {
        exoPlayerView.setVisibility(View.VISIBLE);

        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
            ApplicationInfo applicationInfo = getContext().getApplicationInfo();
            int stringId = applicationInfo.labelRes;
            String applicationName = stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : getContext().getString(stringId);

            String userAgent = Util.getUserAgent(getActivity(), applicationName);
            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(videoUrl), new
                    DefaultDataSourceFactory(getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            exoPlayerView.setPlayer(mExoPlayer);
            mExoPlayer.prepare(mediaSource);

            mExoPlayer.setPlayWhenReady(mPlayerState);
        }
        if (savedPlayerPosition > 0) {
            mExoPlayer.seekTo(savedPlayerPosition);
        }
    }

    private void setDefaultImage(String thumbnail)
    {
        exoPlayerView.setVisibility(View.INVISIBLE);
        defaultImageView.setVisibility(View.VISIBLE);
        if(!thumbnail.equals(""))
        {
            defaultImageView.setImageURI(Uri.parse(thumbnail));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mExoPlayer!= null)
        {
            mPlayerState = mExoPlayer.getPlayWhenReady();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void releasePlayer()
    {
        if (mExoPlayer != null)
        {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

}
