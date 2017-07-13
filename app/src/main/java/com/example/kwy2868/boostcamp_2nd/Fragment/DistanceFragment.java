package com.example.kwy2868.boostcamp_2nd.Fragment;

import android.os.Bundle;

import com.example.kwy2868.boostcamp_2nd.Model.DataForTest;

/**
 * Created by kwy2868 on 2017-07-11.
 */

public class DistanceFragment extends BaseFragment {

    // showType에 대한 정보를 프래그먼트 생성할 때 같이 넘겨준다.
    // 넘겨 줬으니까 받아와야겠지?
    public static DistanceFragment newInstance(boolean showType) {
        DistanceFragment fragment = new DistanceFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean("showType", showType);
        fragment.setArguments(bundle);

        return fragment;
    }

    void init() {
        // 맛집 리스트들에 대한 정보들 세팅해줌.
        // 왜 플래그가 1이들어와.
        FLAG = 0;
        list = new DataForTest(FLAG).getList();
        super.init();
    }
}
