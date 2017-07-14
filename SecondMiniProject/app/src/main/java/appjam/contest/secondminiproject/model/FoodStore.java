package appjam.contest.secondminiproject.model;

/**
 * Created by jeongdahun on 2017. 7. 13..
 */

public class FoodStore {
    private int imageId;
    private String name;
    private String explain;
    private boolean check=false;
    private int likeCount;
    private int oldCount;

    public FoodStore(int imageId, String name, String explain, int likeCount, int oldCount){
        this.imageId=imageId;
        this.name=name;
        this.explain=explain;
        this.likeCount=likeCount;
        this.oldCount=oldCount;
    }

    public int getImageId(){ return imageId;}
    public String getName() { return name;}
    public String getExplain() { return  explain;}
    public boolean getCheck() {return check;}
    public void setCheck() { check = !check; }
    public int getLikeCount() {return likeCount;}
    public int getOldCount() {return  oldCount;}

}
