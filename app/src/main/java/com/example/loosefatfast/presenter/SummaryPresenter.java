package com.example.loosefatfast.presenter;

import com.example.loosefatfast.utils.Video;
import com.example.loosefatfast.utils.YoutubeUtils;
import java.util.List;

public class SummaryPresenter {
    private SummaryView view;

    public SummaryPresenter(SummaryView view) {
        this.view = view;
    }

    public void calculateSummary(String age, String gender, String height, String weight, String targetWeight) {
        String keyword = generateKeyword(age, gender, height, weight, targetWeight);
        searchYoutubeVideos(keyword);
    }

    private String generateKeyword(String age, String gender, String height, String weight, String targetWeight){
        double bmi = calculateBMI(Double.parseDouble(height), Double.parseDouble(weight));
        String fitnessGoal = determineFitnessGoal(Double.parseDouble(weight), Double.parseDouble(targetWeight));

        StringBuilder keywordBuilder = new StringBuilder();
        keywordBuilder.append("fitness ");
        keywordBuilder.append(gender).append(" ");
        keywordBuilder.append(age).append(" years old ");

        if (bmi < 18.5) {
            keywordBuilder.append("underweight ");
        } else if (bmi < 24.9) {
            keywordBuilder.append("normal weight ");
        } else if (bmi < 29.9) {
            keywordBuilder.append("overweight ");
        } else {
            keywordBuilder.append("obesity ");
        }

        keywordBuilder.append(fitnessGoal).append(" workout");

        return keywordBuilder.toString();
    }

    private double calculateBMI(double height, double weight) {
        double heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }

    private String determineFitnessGoal(double weight, double targetWeight) {
        if (targetWeight < weight) {
            return "weight loss";
        } else if (targetWeight > weight) {
            return "muscle gain";
        } else {
            return "fitness maintenance";
        }
    }
    private void searchYoutubeVideos(String keyword) {
        YoutubeUtils.searchVideos(keyword, new YoutubeUtils.YoutubeCallback() {
            @Override
            public void onSuccess(List<Video> videos) {
                view.onVideosLoaded(videos);
            }

            @Override
            public void onError(String message) {
                view.onError(message);
            }
        });
    }

    public interface SummaryView {
        void onVideosLoaded(List<Video> videos);
        void onError(String message);
    }
}
