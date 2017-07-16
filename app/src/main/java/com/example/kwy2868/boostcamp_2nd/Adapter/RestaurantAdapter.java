package com.example.kwy2868.boostcamp_2nd.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kwy2868.boostcamp_2nd.R;
import com.example.kwy2868.boostcamp_2nd.ViewHolder.RestaurantViewHolder;

import java.util.ArrayList;

/**
 * Created by kwy2868 on 2017-07-11.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
    // 레스토랑 수.
    private int count;

    private RestaurantViewHolder restaurantViewHolder;
    private ArrayList restaurantList;

    // 어댑터 만들때 데이터들도 같이 넘겨주자.
    public RestaurantAdapter(int count, ArrayList list) {
        this.count = count;
        this.restaurantList = list;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        // 각 아이템마다 이 레이아웃을 통해 전개.
        int layoutForListItem = R.layout.restaurant_cardview;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutForListItem, parent, shouldAttachToParentImmediately);
        restaurantViewHolder = new RestaurantViewHolder(view);
        restaurantViewHolder.setList(restaurantList);

        return restaurantViewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
//        Log.d("Item Number", "#" + position);
        // 이제 여기서 바인드 해주자.
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return count;
    }
}
