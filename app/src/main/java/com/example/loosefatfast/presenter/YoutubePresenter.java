package com.example.loosefatfast.presenter;

import com.example.loosefatfast.utils.Video;
import com.example.loosefatfast.utils.YoutubeUtils;
import com.example.loosefatfast.view.SummaryActivity;

import java.util.List;

public class YoutubePresenter {
    private SummaryActivity view;

    public YoutubePresenter(SummaryActivity view) {
        this.view = view;
    }

    public void fetchVideos(String query) {
        YoutubeUtils.searchVideos(query, new YoutubeUtils.YoutubeCallback() {
            @Override
            public void onSuccess(List<Video> videos) {
                view.displayVideos(videos);
            }

            @Override
            public void onError(String message) {
                view.showError(message);
            }
        });
    }
}
