package com.example.doublejk.project2;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static List<RestaurantItem> restaurants;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private Toolbar toolbar;
    private ImageButton layoutChangBtn;
    private boolean layoutType = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }
    //초기화
    public void Init() {
        createRestaurant();
        toolbarInit();
        viewPagerInit();
        changeLayout();
        sortingOfType();
    }
    //화면에 뿌려줄 Restuarant들의 객체들을 저장
    public List<RestaurantItem> createRestaurant() {
        restaurants = new ArrayList<>();
        restaurants.add(new RestaurantItem(R.drawable.post1, "연남서식당", getString(R.string.standrip), 2, 1, 3));
        restaurants.add(new RestaurantItem(R.drawable.post2, "만푸쿠", getString(R.string.manpuku), 0, 3, 2));
        restaurants.add(new RestaurantItem(R.drawable.post3, "수정식당", getString(R.string.sujung), 1, 2, 0));
        restaurants.add(new RestaurantItem(R.drawable.post4, "호야초밥참치", getString(R.string.hoya), 3, 0, 1));
        restaurants.add(new RestaurantItem(R.drawable.post5, "원조부산족발", getString(R.string.busan), 4, 4, 4));

        return restaurants;
    }
    //viewPager 초기화
    public void viewPagerInit() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
    //toolbar 초기화
    public void toolbarInit() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("맛집리스트");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //화살표
        //getSupportActionBar().setHomeAsUpIndicator(); // 화살표 이미지 변경

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(false);
        //collapsingToolbar.setContentScrimColor(Color.GREEN); //툴바가 사라지는 동안 색상 지정
    }
    //버튼 클릭시 레이아웃(linear, staggerdGrid) 변경 이벤트 처리 함수
    public void changeLayout() {
        layoutChangBtn = (ImageButton) findViewById(R.id.layoutChangeBtn);
        layoutChangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("버튼", "bool " + layoutType);
                viewPagerAdapter.setLayoutType(layoutType);
                viewPagerAdapter.notifyDataSetChanged();
                if(layoutType == false) {
                    layoutChangBtn.setImageResource(R.drawable.staggerd);
                    layoutType = true;
                }
                else {
                    layoutChangBtn.setImageResource(R.drawable.linear);
                    layoutType = false;
                }
            }
        });
    }

    //거리순, 인기순, 최신순 타입별로 정렬 이벤트처리 함수
    public void sortingOfType() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Log.d("온pageSeleted", ""+position);
                viewPagerAdapter.setSortType(position);
                viewPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}