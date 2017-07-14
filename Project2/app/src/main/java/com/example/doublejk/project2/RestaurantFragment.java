package com.example.doublejk.project2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayout;
    private StaggeredGridLayoutManager staggeredGridLayout;
    private List<RestaurantItem> restaurants;
    public static RestaurantFragment newInstance(boolean layoutType, int sortType) {
        RestaurantFragment fragment = new RestaurantFragment();
        Bundle args = new Bundle();
        //Log.d("인스턴스", "" + layoutType + " " + sortType);
        args.putBoolean("layoutType", layoutType);
        args.putInt("SortType", sortType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.distanceRecycler);

        Bundle args = getArguments();
        boolean layoutType = args.getBoolean("layoutType");
        //Log.d("onCreateView", "" + layoutType);
        //Fragment 레이아웃 설정
        if(!layoutType) {
            linearLayout = new LinearLayoutManager(getActivity());
            linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayout);
            //Toast.makeText(getActivity(), "linear", Toast.LENGTH_SHORT).show();
        } else {
            staggeredGridLayout = new StaggeredGridLayoutManager(2, 1);
            recyclerView.setLayoutManager(staggeredGridLayout);
            //Toast.makeText(getActivity(), "staggerd", Toast.LENGTH_SHORT).show();
        }

        int sortType = args.getInt("SortType");
        restaurants = MainActivity.restaurants;
        RestaurantComparator comparator = new RestaurantComparator(sortType);
        Collections.sort(restaurants, comparator);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), restaurants);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}
