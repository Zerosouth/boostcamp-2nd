package com.example.kwy2868.boostcamp_2nd.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.kwy2868.boostcamp_2nd.Model.Restaurant;
import com.example.kwy2868.boostcamp_2nd.R;

/**
 * Created by kwy2868 on 2017-07-14.
 */

public class DetailActivity extends AppCompatActivity {

    private static final String DETAIL_TAG = "RESTAURANT";

    private TextView detailName;
    private TextView detailDistance;
    private TextView detailPopularity;
    private TextView detailLatest;
    private TextView detailContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        Log.d("Bundle", "Bundle : " + savedInstanceState);
        // 바인드 작업.
        init();
    }

    void init(){
        // 맛집 객체를 받아온다.
        Restaurant restaurant = getIntent().getParcelableExtra(DETAIL_TAG);
        Log.d("Restaurant 정보 잘 받아오냐?", "Restaurant : " + restaurant);
        Log.d("받은 맛집 이름", restaurant.getName());

        detailName = (TextView)findViewById(R.id.detail_name);
        detailDistance = (TextView) findViewById(R.id.detail_distance);
        detailPopularity = (TextView) findViewById(R.id.detail_popularity);
        detailLatest = (TextView)findViewById(R.id.detail_latest);
        detailContent = (TextView)findViewById(R.id.detail_content);

        detailName.setText(restaurant.getName());
        detailDistance.setText(restaurant.getDistance()+"");
        detailPopularity.setText(restaurant.getPopularity()+"");
        detailLatest.setText(restaurant.getLatest()+"");
        detailContent.setText(restaurant.getContent());
    }
}
