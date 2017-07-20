package appjam.contest.thirdminiproject.main.model;

/**
 * Created by jeongdahun on 2017. 7. 19..
 */

public class Place {
    private String title;
    private String location;
    private String num;
    private String explain;
    private double latitude;
    private double longitude;


    public Place(String title, String location, String num, String explain, double latitude, double longitude) {
        this.title = title;
        this.location = location;
        this.num = num;
        this.explain = explain;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getNum() {
        return num;
    }

    public String getExplain() {
        return explain;
    }

    public double getLatitude() {return latitude;}

    public double getLongitude() {return longitude;}
}
