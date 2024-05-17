package com.example.loosefatfast.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loosefatfast.R;
import com.example.loosefatfast.presenter.SummaryPresenter;
import com.example.loosefatfast.utils.Video;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity implements SummaryPresenter.SummaryView {
    private SummaryPresenter presenter;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayer;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        RecyclerView videosRecyclerView = findViewById(R.id.videos_recycler_view);
        videosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        videoAdapter = new VideoAdapter(new ArrayList<>(), video -> {
            if (youTubePlayer != null) {
                youTubePlayer.loadVideo(video.getVideoId(), 0);
            }
            youTubePlayerView.setVisibility(View.VISIBLE);
        });

        videosRecyclerView.setAdapter(videoAdapter);

        presenter = new SummaryPresenter(this);

        // Get data from the intent
        Intent intent = getIntent();
        String age = intent.getStringExtra("userAge");
        String gender = intent.getStringExtra("userGender");
        String height = intent.getStringExtra("userHeight");
        String weight = intent.getStringExtra("userWeight");
        String targetWeight = intent.getStringExtra("userTargetWeight");

        // Call calculateSummary with the required arguments
        presenter.calculateSummary(age, gender, height, weight, targetWeight);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer initializedYouTubePlayer) {
                youTubePlayer = initializedYouTubePlayer;
            }
        });
    }

    @Override
    public void onVideosLoaded(List<Video> videos) {
        videoAdapter.setVideos(videos);
        videoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void displayVideos(List<Video> videos) {
        videoAdapter.setVideos(videos);
        videoAdapter.notifyDataSetChanged();
    }

    public void showError(String message) {
        Toast.makeText(SummaryActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
