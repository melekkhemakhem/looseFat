package com.example.loosefatfast.utils;

import android.os.AsyncTask;
import android.util.Log;
import com.example.loosefatfast.utils.Config;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class YoutubeUtils {

    public interface YoutubeCallback {
        void onSuccess(List<Video> videos);
        void onError(String message);
    }

    public static void searchVideos(String query, YoutubeCallback callback) {
        new FetchYoutubeVideosTask(query, callback).execute();
    }

    private static class FetchYoutubeVideosTask extends AsyncTask<Void, Void, List<Video>> {
        private String query;
        private YoutubeCallback callback;

        public FetchYoutubeVideosTask(String query, YoutubeCallback callback) {
            this.query = query;
            this.callback = callback;
        }

        @Override
        protected List<Video> doInBackground(Void... voids) {
            List<Video> videos = new ArrayList<>();
            try {
                String apiKey = Config.YOUTUBE_API_KEY;
                String urlString = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + query + "&key=" + apiKey;
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray items = jsonResponse.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    JSONObject id = item.getJSONObject("id");
                    String videoId = id.getString("videoId");
                    JSONObject snippet = item.getJSONObject("snippet");
                    String title = snippet.getString("title");
                    String thumbnailUrl = snippet.getJSONObject("thumbnails").getJSONObject("default").getString("url");
                    videos.add(new Video(videoId, title, thumbnailUrl));
                }
            } catch (Exception e) {
                Log.e("YoutubeUtils", "Error fetching YouTube videos", e);
                return null;
            }
            return videos;
        }

        @Override
        protected void onPostExecute(List<Video> videos) {
            if (videos != null && !videos.isEmpty()) {
                callback.onSuccess(videos);
            } else {
                callback.onError("Failed to load videos");
            }
        }
    }
}
