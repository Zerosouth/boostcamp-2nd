package com.example.kwy2868.boostcamp_2nd.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kwy2868.boostcamp_2nd.Adapter.ViewPagerAdapter;
import com.example.kwy2868.boostcamp_2nd.Model.Restaurant;
import com.example.kwy2868.boostcamp_2nd.R;
import com.example.kwy2868.boostcamp_2nd.View.MainActivity;

import java.util.ArrayList;

/**
 * Created by kwy2868 on 2017-07-11.
 */

public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView restaurantImage;
    private TextView restaurantName;
    private ImageButton checkbox;
    private TextView restaurantContent;

    // 이걸 받아와야 프래그먼트끼리 정보 전달이 됨.
    private ViewPagerAdapter viewPagerAdapter;

    // 레스토랑 인스턴스들이 담겨있는 어레이리스트.
    private ArrayList list;

    public RestaurantViewHolder(View itemView) {
        super(itemView);
        MainActivity mainActivity = (MainActivity)itemView.getContext();
        viewPagerAdapter = mainActivity.getViewPagerAdapter();

        restaurantImage = (ImageView) itemView.findViewById(R.id.restaurant_image);
        restaurantName = (TextView) itemView.findViewById(R.id.restaurant_name);
        checkbox = (ImageButton) itemView.findViewById(R.id.checkbox);
        // 이번엔 이런 방식으로 리스너 달아볼까.
        checkbox.setOnClickListener(this);
//        checkbox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        restaurantContent = (TextView) itemView.findViewById(R.id.restaurant_content);
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    public void bind(int position) {
        Restaurant restaurant = (Restaurant) list.get(position);
//        Log.d("Restaurant Name", "Restaurant Name : " + restaurant.getName());
//        Log.d("Restaurant Content", "Restaurant Content : " + restaurant.getContent());
        restaurantImage.setImageResource(restaurant.getImage());
        restaurantName.setText(restaurant.getName());
        // check 되어있으면, true이면.
        if (restaurant.is_checked()) {
            checkbox.setImageResource(R.drawable.ic_check_box_black_24dp);
        } else {
            checkbox.setImageResource(R.drawable.ic_check_box_white_24dp);
        }
        restaurantContent.setText(restaurant.getContent());
    }

    @Override
    public void onClick(View v) {
        // position 정보를 받아온다.
        int position = getAdapterPosition();
        Restaurant restaurant = (Restaurant) list.get(position);
        // 현재 체크되어 있는 상태면.
        if (restaurant.is_checked()) {
//            restaurant.setIs_checked(false);
            Log.d("아이템 체크 잘 될까?", "Position : " + position + " 체크 풀어주자");
            viewPagerAdapter.NotifyCheckBox(restaurant);
//            restaurantAdapter.notifyDataSetChanged();
        } else {
//            restaurant.setIs_checked(true);
            Log.d("아이템 체크 잘 해제될까?", "Position : " + position + " 체크 해주자");
            viewPagerAdapter.NotifyCheckBox(restaurant);
//            restaurantAdapter.notifyDataSetChanged();
        }
    }
}
