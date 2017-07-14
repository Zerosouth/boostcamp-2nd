package lsw.boostcamp2project;

import android.media.Image;

/**
 * Created by lsw38 on 2017-07-11.
 */

public class Item {
    int distance;
    int popularity;
    int update;
    int image;
    int isCheck= 0;
    String content_title;
    String context;


    public Item(int distance, int popularity, int update, int image, String content_title, String context) {
        this.distance = distance;
        this.popularity = popularity;
        this.update = update;
        this.image = image;
        this.content_title = content_title;
        this.context = context;
    }

    public int getCheck() {
        return isCheck;
    }

    public void setCheck(int check) {
        this.isCheck = check;
    }

    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }
}
