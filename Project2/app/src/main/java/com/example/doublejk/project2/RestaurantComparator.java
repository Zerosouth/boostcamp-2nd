package com.example.doublejk.project2;

import java.util.Comparator;

/**
 * Created by doublejk on 2017-07-13.
 */

//Restaurant 정렬을 위한 각 주제별 Rank 비교
public class RestaurantComparator implements Comparator<RestaurantItem>{
    private int compareType;
    public RestaurantComparator(int compareType) {
        this.compareType = compareType;
    }
    @Override
    public int compare(RestaurantItem r1, RestaurantItem r2) {
        int rank_first = 0;
        int rank_second = 0;
        switch (compareType) {
            case 0:
                rank_first = r1.getDistanceRank();
                rank_second = r2.getDistanceRank();
                break;
            case 1:
                rank_first = r1.getPopularRank();
                rank_second = r2.getPopularRank();
                break;
            case 2:
                rank_first = r1.getRecentRank();
                rank_second = r2.getRecentRank();
                break;
        }
        if(rank_first > rank_second)
            return 1;
        else if(rank_first < rank_second)
            return -1;
        else
            return 0;
    }
}
