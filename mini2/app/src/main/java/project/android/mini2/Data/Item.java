package project.android.mini2.Data;

/**
 * Created by qkrqh on 2017-07-11.
 */

public class Item {

    String name;
    int img;
    int content;
    boolean check;
    int distance;
    int recent;
    int popular;

    public Item(){

    }

    public Item(String name, int img, int content, boolean check, int distance, int recent, int popular) {
        this.name = name;
        this.img = img;
        this.content = content;
        this.check = check;
        this.distance = distance;
        this.recent = recent;
        this.popular = popular;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public boolean getCheck() {
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

    public int getRecent() {
        return recent;
    }

    public void setRecent(int recent) {
        this.recent = recent;
    }

    public int getPopular() {
        return popular;
    }

    public void setPopular(int popular) {
        this.popular = popular;
    }
}
