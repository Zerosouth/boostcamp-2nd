package com.example.kwy2868.boostcamp_2nd.Model;

import com.example.kwy2868.boostcamp_2nd.R;

import java.util.ArrayList;

/**
 * Created by kwy2868 on 2017-07-11.
 */

public class DataForTest {
    private ArrayList<Restaurant> list;

    private static Restaurant haeuso
            = new Restaurant(R.drawable.haeuso, "해우소", "꿀막걸리가 인기메뉴고 맛있는 음식을 저렴하게 즐길 수 있는 해우소입니다.", 4, 3, 2);
    private static Restaurant samgyori
            = new Restaurant(R.drawable.samgyori, "삼교리동치미막국수", "막국수를 비빔으로 먹을지 물로 먹을지 고객이 만들어 먹는 삼교리동치미막국수입니다.", 3, 5, 1);
    private static Restaurant ohya
            = new Restaurant(R.drawable.ohya, "오야오야", "오야오야입니다.", 2, 4, 3);
    private static Restaurant ggachi
            = new Restaurant(R.drawable.ggachi, "까치둥지", "까치둥지입니다.", 1, 2, 4);

    public DataForTest(){
        list = new ArrayList<>();
        list.add(haeuso);
        list.add(samgyori);
        list.add(ohya);
        list.add(ggachi);
    }

    // 거리, 인기, 최근순.
    public DataForTest(int FLAG) {
        list = new ArrayList<>();
        // 거리순.
        if (FLAG == 0) {
            list.add(haeuso);
            list.add(samgyori);
            list.add(ohya);
            list.add(ggachi);
        }
        // 인기순
        else if (FLAG == 1) {
            list.add(samgyori);
            list.add(ohya);
            list.add(haeuso);
            list.add(ggachi);
        }
        // 최신순
        else {
            list.add(ggachi);
            list.add(ohya);
            list.add(haeuso);
            list.add(samgyori);
        }
    }

    public ArrayList<Restaurant> getList() {
        return list;
    }
}
