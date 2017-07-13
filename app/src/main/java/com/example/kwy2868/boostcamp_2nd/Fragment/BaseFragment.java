package com.example.kwy2868.boostcamp_2nd.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kwy2868.boostcamp_2nd.Adapter.RestaurantAdapter;
import com.example.kwy2868.boostcamp_2nd.R;

import java.util.ArrayList;

/**
 * Created by kwy2868 on 2017-07-12.
 */

public abstract class BaseFragment extends Fragment {

    protected static final int COLUMN_SPAN = 2;
    protected static final int RESTAURANT_COUNT = 4;

    // 프래그먼트마다 갖고 있는 각기 다른 플래그 값.
    protected int FLAG;

    protected boolean showType;

    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    protected RestaurantAdapter restaurantAdapter;

    // 맛집 리스트들.
    protected ArrayList list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // showType에 대한 정보를 저장해준다.
        if (getArguments() != null) {
            showType = getArguments().getBoolean("showType");
//            Log.d("정상적으로 showType 전달", showType + " ");
        }
        // 초기화 작업.
        init();
    }

    void init() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview);
//        Log.d("RecyclerView", "RecyclerView : " + recyclerView);
//        Log.d("Fragment의 showType", "Fragment의 showType : " + showType);
        // 리스트 방식으로 보는 경우.
        if (showType == true) {
            // 기본적으로 vertical.
            layoutManager = new LinearLayoutManager(getContext());
        } else {
            // 리니어레이아웃매니저쓰면 잘 나오는데 왜 이거쓰면 방향이 살짝 이상할까. 질문!!!!!!!!!!!!!!
            layoutManager = new StaggeredGridLayoutManager(COLUMN_SPAN, StaggeredGridLayoutManager.VERTICAL);
        }
//        Log.d("LayoutManager", "LayoutManager : " + layoutManager);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        restaurantAdapter = new RestaurantAdapter(RESTAURANT_COUNT, list);
//        Log.d("RVAdapter", "RVAdapter : " + adapter);
        recyclerView.setAdapter(restaurantAdapter);
//        int position = recyclerView.getChildLayoutPosition();
    }

    // 리스트가 다 다른데 같은 아이템을 찾아보자.

    public ArrayList getList() {
        return list;
    }

//    public void NotifyLayoutChange(boolean showType) {
//        if (!showType) {
//            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(COLUMN_SPAN, StaggeredGridLayoutManager.VERTICAL));
//            recyclerView.getAdapter().notifyDataSetChanged();
//        } else {
//            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            recyclerView.getAdapter().notifyDataSetChanged();
//        }
//    }

    // 실패작.
//    public void changeLayoutMangager(boolean showType) {
//        if (showType == true) {
//            layoutManager = new LinearLayoutManager(getContext());
//        } else {
//            layoutManager = new StaggeredGridLayoutManager(COLUMN_SPAN, StaggeredGridLayoutManager.VERTICAL);
//        }
//        Log.d("change showType", "showType : " + showType);
//        Log.d("After Click", "LayoutManager : " + layoutManager);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new RestaurantAdapter(RESTAURANT_COUNT, list, showType);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }
}
