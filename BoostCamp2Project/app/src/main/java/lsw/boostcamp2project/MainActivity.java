package lsw.boostcamp2project;

import android.graphics.Color;
import android.media.Image;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int type = 0;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item(1,3,5,R.drawable.ilak, "일락", "왕십리에 위치한 규동, 일본식라면 맛집. 점심엔 바글바글"));
        items.add(new Item(2,5,1,R.drawable.nogari, "노가리슈퍼", "왕십리 시장에 위치한 술집. 저렴함. 프랜차이즈"));
        items.add(new Item(3,1,3,R.drawable.ohyangjokbal, "오향족발", "시청에 위치한 족발맛집, 서울 3대 족발 가게, 저녁에 줄서서 기다림"));
        items.add(new Item(4,4,2,R.drawable.tosang, "토상막회", "상왕십리에 위치한 횟집, 할머니 할아버지 내외가 운영, 랍스터가 들어간 해물라면이 유명"));
        items.add(new Item(5,2,4,R.drawable.yuk, "육회집", "광장시장에 위치한 육회집, 비교적 저렴한 육회가격, 무조건 소주가 필요함 안마시면 죄 짓는 것"));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("거리순"));
        tabLayout.addTab(tabLayout.newTab().setText("인기순"));
        tabLayout.addTab(tabLayout.newTab().setText("최근순"));
        tabLayout.setTabTextColors(Color.BLUE, Color.BLACK);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        final TabPagerAdapter tabpagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), items);

        //각 탭별 프래그먼트 정보를 어댑터로부터 얻어옴
        final DistanceTabFragment distanceTabfragment = tabpagerAdapter.getDistanceTabFragment();
        final PopularityTabFragment popularityTabFragment  = tabpagerAdapter.getPopularityTabFragment();
        final RecentTabFragment recentTabFragment = tabpagerAdapter.getRecentTabFragment();
        distanceTabfragment.setTabPagerAdapter(tabpagerAdapter);
        popularityTabFragment.setTabPagerAdapter(tabpagerAdapter);
        recentTabFragment.setTabPagerAdapter(tabpagerAdapter);

        viewPager.setAdapter(tabpagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // 각 프레그먼트의 레이아웃 타입을 뭘로 바꿀껀지 알리고, adapter에 type 바뀌었다고 알림.
        final ImageButton layoutBtn = (ImageButton) findViewById(R.id.layoutChangeButton);
        layoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type == 0){
                    type = 1;
                    distanceTabfragment.type = type;
                    popularityTabFragment.type = type;
                    recentTabFragment.type = type;
                    layoutBtn.setImageResource(R.drawable.ic_stagger);

                    tabpagerAdapter.notifyDataSetChanged();

                }else{
                    type = 0;
                    distanceTabfragment.type = type;
                    popularityTabFragment.type = type;
                    recentTabFragment.type = type;
                    layoutBtn.setImageResource(R.drawable.ic_linear);

                    tabpagerAdapter.notifyDataSetChanged();

                }

            }
        });




    }

    public ViewPager getViewPager(){
        return this.viewPager;
    }
}
