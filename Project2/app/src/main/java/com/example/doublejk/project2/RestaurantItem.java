package com.example.doublejk.project2;

/**
 * Created by doublejk on 2017-07-12.
 */

public class RestaurantItem {
    private int cardImg, checkImg;
    private String title, preview;
    private boolean isChecked;
    private int distanceRank, popularRank, recentRank;

    public RestaurantItem(int cardImg, String title, String preview, int distanceRank, int popularRank, int recentRank) {
        this.cardImg = cardImg;
        this.title = title;
        this.preview = preview;
        this.isChecked = false;
        this.distanceRank = distanceRank;
        this.popularRank = popularRank;
        this.recentRank = recentRank;
    }

    public int getCardImg() {
        return cardImg;
    }

    public int getDistanceRank() {
        return distanceRank;
    }

    public void setDistanceRank(int distanceRank) {
        this.distanceRank = distanceRank;
    }

    public int getPopularRank() {
        return popularRank;
    }

    public void setPopularRank(int popularRank) {
        this.popularRank = popularRank;
    }

    public int getRecentRank() {
        return recentRank;
    }

    public void setRecentRank(int recentRank) {
        this.recentRank = recentRank;
    }

    public void setCardImg(int cardImg) {
        this.cardImg = cardImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
