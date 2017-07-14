package project.android.mini2.Main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import project.android.mini2.Data.Item;
import project.android.mini2.Fragment.BaseFragment;
import project.android.mini2.R;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    public static ArrayList items;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    void init(){

        setToolbar();

        setTabBar();

        container = (LinearLayout)findViewById(R.id.container);

        items = new ArrayList<Item>();
        items.add(new Item("해우소",R.drawable.content_img1, R.string.content1, false, 2, 3, 5 ));
        items.add(new Item("삼교리동치미막국수",R.drawable.content_img2, R.string.content2, false, 3, 0, 4  ));
        items.add(new Item("뚱아뚱아오릿대",R.drawable.content_img3, R.string.content3, false, 4, 4, 3  ));
        items.add(new Item("카레곰빵",R.drawable.content_img4, R.string.content4, false, 5, 2, 2  ));
        items.add(new Item("매일식당",R.drawable.content_img5, R.string.content5, true, 0, 8, 4 ));

    }

    void setTabBar(){

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("거리순"));
        tabLayout.addTab(tabLayout.newTab().setText("인기순"));
        tabLayout.addTab(tabLayout.newTab().setText("최근순"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        replaceFragment(BaseFragment.newInstance(0));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                replaceFragment(BaseFragment.newInstance(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        View viewToolbar = getLayoutInflater().inflate(R.layout.tool_bar, null);
        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
    }

    void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);

        transaction.commit();
    }


}
