package com.example.kwy2868.boostcamp_2nd.View;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kwy2868.boostcamp_2nd.Adapter.ViewPagerAdapter;
import com.example.kwy2868.boostcamp_2nd.Fragment.BaseFragment;
import com.example.kwy2868.boostcamp_2nd.Fragment.DistanceFragment;
import com.example.kwy2868.boostcamp_2nd.Fragment.LatestFragment;
import com.example.kwy2868.boostcamp_2nd.Fragment.PopularityFragment;
import com.example.kwy2868.boostcamp_2nd.Interface.ButtonClickListener;
import com.example.kwy2868.boostcamp_2nd.R;


/////////////MVP 패턴을 이용하고 싶습니다 튜터님!!/////////////
/////////////제 코드에서 Restaurant와 DataForTest는 Model, 어댑터와 인터페이스와 뷰홀더는 Presenter///////////////
/////////////액티비티와 프래그먼트는 View로 구분하면 되는건가요!!/////////////////

public class MainActivity extends AppCompatActivity implements ButtonClickListener {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ImageButton showtypebtn;
    private ViewPager viewPager;

    private ViewPagerAdapter viewPagerAdapter;

    private static final int TAB_COUNT = 3;

    // showType이 false이면 지그재그. true인 경우는 리스트.
    private boolean showType = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // bind 해주는 초기화 작업.
        init();
    }

    void init() {
//        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbarLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // 툴바 좌우 여백을 없애줌.
        toolbar.setContentInsetsAbsolute(0, 0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        getSupportActionBar().setTitle(getString(R.string.toolbar_title));

        // 하드코딩하지말고 이런식으로 코딩하는 습관 들이자.
        // 근데 왜 툴바 타이틀이 안생길까..? 물어보자.!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        collapsingToolbarLayout.setTitle(getString(R.string.toolbar_title));

//        Log.d("title", "툴바 이름 : " + getString(R.string.toolbar_title));

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        // 어떤 방식으로 볼건지 데이터 넘겨준다.
        viewPagerAdapter.setShowType(showType);
        viewPagerAdapter.setFragmentArraySize(TAB_COUNT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
//        Log.d("ViewPager", "ViewPager : " + viewPager);
//        Log.d("ViewPagerAdapter", "ViewPagerAdapter : " + viewPagerAdapter);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.distance));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.popularity));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.latest));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                BaseFragment fragment = viewPagerAdapter.getFragmentArray()[viewPager.getCurrentItem()];
                if (viewPager.getCurrentItem() == 0) {
                    DistanceFragment distanceFragment = (DistanceFragment) fragment;
//                    Log.d("FLAG", "FLAG : " + distanceFragment.getFLAG());
                }
                if (viewPager.getCurrentItem() == 1) {
                    PopularityFragment popularityFragment = (PopularityFragment) fragment;
//                    Log.d("FLAG", "FLAG : " + popularityFragment.getFLAG());
                }
                if (viewPager.getCurrentItem() == 2) {
                    LatestFragment latestFragment = (LatestFragment) fragment;
//                    Log.d("FLAG", "FLAG : " + latestFragment.getFLAG());
                }
//                Log.d("Current Page", "Current Page : " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 이거하면 탭이 셀렉트 되었을 때 색이 바뀐다.
                tabLayout.getTabAt(position).select();
//                Log.d("Current Page", "현재 페이지 : " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        showtypebtn = (ImageButton) findViewById(R.id.showtypebtn);
        showtypebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTypeButtonClick();
            }
        });

    }

    public ViewPagerAdapter getViewPagerAdapter() {
        return viewPagerAdapter;
    }

    // 뷰타입 바꿔주는 함수 내용.
    // 버튼 누르면 호출됨.
    @Override
    public void showTypeButtonClick() {
        // 거리순
        // 현재 리스트 방식으로 보고 있는 경우.
        if (showType == true) {
            showType = false;
            Toast.makeText(this, "지그재그 방식으로 변경", Toast.LENGTH_SHORT).show();
            showtypebtn.setImageResource(R.drawable.ic_view_agenda_black_24dp);
        }
        // 현재 Staggered 방식으로 보고 있는 경우.
        else {
            showType = true;
            Toast.makeText(this, "리스트 방식으로 변경", Toast.LENGTH_SHORT).show();
            showtypebtn.setImageResource(R.drawable.ic_dashboard_black_24dp);
        }
        viewPagerAdapter.setShowType(showType);
        viewPagerAdapter.notifyDataSetChanged();
    }
}
