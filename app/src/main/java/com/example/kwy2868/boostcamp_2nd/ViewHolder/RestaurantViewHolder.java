package com.example.kwy2868.boostcamp_2nd.ViewHolder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwy2868.boostcamp_2nd.Adapter.ViewPagerAdapter;
import com.example.kwy2868.boostcamp_2nd.Model.Restaurant;
import com.example.kwy2868.boostcamp_2nd.R;
import com.example.kwy2868.boostcamp_2nd.View.DetailActivity;
import com.example.kwy2868.boostcamp_2nd.View.MainActivity;

import java.util.ArrayList;

/**
 * Created by kwy2868 on 2017-07-11.
 */

public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String DETAIL_TAG = "RESTAURANT";

    private ImageView restaurantImage;
    private TextView restaurantName;
    private ImageButton replyButton;
    private ImageButton checkbox;
    private TextView restaurantContent;

    private MainActivity mainActivity;

    // 이걸 받아와야 프래그먼트끼리 정보 전달이 됨.
    private ViewPagerAdapter viewPagerAdapter;

    // 레스토랑 인스턴스들이 담겨있는 어레이리스트.
    private ArrayList list;

    public RestaurantViewHolder(View itemView) {
        super(itemView);
        // 아이템 클릭하면 상세화면 보는 액티비티 하나 띄워주자.
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetail();
            }
        });
        mainActivity = (MainActivity)itemView.getContext();
        viewPagerAdapter = mainActivity.getViewPagerAdapter();

        restaurantImage = (ImageView) itemView.findViewById(R.id.restaurant_image);
        restaurantName = (TextView) itemView.findViewById(R.id.restaurant_name);
        replyButton = (ImageButton) itemView.findViewById(R.id.reply);
        // 맛집에 대한 의견 남기는 창, 다이얼로그 띄워주자.
        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDIalog();
            }
        });
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
        replyButton.setImageResource(R.drawable.ic_create_black_24dp);

        // check 되어있으면, true이면.
        if (restaurant.is_checked()) {
            checkbox.setImageResource(R.drawable.ic_check_circle_black_24dp);
        } else {
            checkbox.setImageResource(R.drawable.ic_check_circle_white_24dp);
        }
        restaurantContent.setText(restaurant.getContent());
    }

    // 새로운 화면 띄워줘서 맛집 정보를 보여주자.
    public void showDetail(){
        // list 중에서 클릭한 아이템의 포지션 값을 받아온다.
        int position = getAdapterPosition();
        Restaurant restaurant = (Restaurant) list.get(position);
        Log.d("보낼 맛집 이름", restaurant.getName());

        Intent intent = new Intent(mainActivity, DetailActivity.class);
        intent.putExtra(DETAIL_TAG, restaurant);

        // 액티비티 전환.
        mainActivity.startActivity(intent);
    }


    // Dialog창 띄워주는 함수.
    public void showDIalog(){
        // 어떤 맛집을 선택했는지 position 값을 받아온다.
        int position = getAdapterPosition();
        Restaurant tempRestaurant = (Restaurant) list.get(position);
        // 맛집 이름 정보를 받아오자.
        String restaurantName = tempRestaurant.getName();

        Log.d("Reply", "Reply 달자");
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View view = inflater.inflate(R.layout.dialog_reply, null);
        // 바인드 해줌.
        TextView dialogName = (TextView) view.findViewById(R.id.dialogName);
        dialogName.setText(restaurantName);

        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setView(view);
        builder.setPositiveButton("작성", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mainActivity, "정상적으로 의견을 남겼습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mainActivity, "이전 화면으로 돌아갑니다", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        // 다이얼로그 바깥쪽 터치해도 없어지지 않게 설정.
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
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
