package company.co.kr.project02.model;

/**
 * Created by Dongjin on 2017. 7. 11..
 */

public class Item {
    int imgSrc;
    String title;
    String content;
    boolean check;

    int distance;
    int like;
    int date;

    public Item(int imgSrc, String title, String content, boolean check, int distance, int like, int date) {
        this.imgSrc = imgSrc;
        this.title = title;
        this.content = content;
        this.check = check;
        this.distance = distance;
        this.like = like;
        this.date = date;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Item{" +
                "imgSrc=" + imgSrc +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", check=" + check +
                ", distance=" + distance +
                ", like=" + like +
                ", date=" + date +
                '}';
    }
}
