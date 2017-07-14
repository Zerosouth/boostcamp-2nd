package project.android.mini2.Main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import project.android.mini2.Data.Item;
import project.android.mini2.Fragment.BaseFragment;
import project.android.mini2.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout tabLayout;
    public static ArrayList items;
    LinearLayout container;

    public static final int up = 1;
    public static final int down = -1;
    public static int order = up;

    int recentPosition =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    void init(){

        setTabBar();

        container = (LinearLayout)findViewById(R.id.content_main);

        items = new ArrayList<Item>();
        items.add(new Item("해우소",R.drawable.content_img1, R.string.content1, false, 2, 3, 5 ));
        items.add(new Item("삼교리동치미막국수",R.drawable.content_img2, R.string.content2, false, 3, 0, 6  ));
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

                recentPosition = tab.getPosition();
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

    void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_main, fragment);

        transaction.commit();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.up) {
            order= up;
            Toast.makeText(MainActivity.this, "오름차순으로 변경", Toast.LENGTH_LONG).show();

        } else if (id == R.id.down) {
            order= down;
            Toast.makeText(MainActivity.this, "내림차순으로 변경", Toast.LENGTH_LONG).show();
        }

        replaceFragment(BaseFragment.newInstance(recentPosition));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
