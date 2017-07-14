package appjam.contest.secondminiproject.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import appjam.contest.secondminiproject.R;
import appjam.contest.secondminiproject.adapter.ViewPagerAdapter;
import appjam.contest.secondminiproject.application.ApplicationController;
import appjam.contest.secondminiproject.tab.PagerFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity{

    //Navigation Setting
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.street_layout) LinearLayout streetLayout;
    @BindView(R.id.popular_layout) LinearLayout popularLayout;
    @BindView(R.id.recent_layout) LinearLayout recentLayout;


    //ViewPager Setting
    @BindView(R.id.pager) ViewPager pager;
    @BindView(R.id.tabs) TabLayout tabs;
    @BindView(R.id.layoutChangeBtn) ImageView layoutChangeBtn;


    ViewPagerAdapter viewPagerAdapter;

    boolean firstLayout=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigationSetting();
        viewPagerSetting();
    }

    private void navigationSetting(){

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("맛집리스트");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        CircleImageView profileImage=(CircleImageView)findViewById(R.id.profile_image);
        Glide.with(getApplicationContext()).load(ApplicationController.user_profile).into(profileImage);

        TextView nameText=(TextView)findViewById(R.id.header_name);
        nameText.setText(ApplicationController.user_name);


    }

    public void navClick(View v){

        streetLayout.setBackgroundColor(Color.rgb(255,255,255));
        popularLayout.setBackgroundColor(Color.rgb(255,255,255));
        recentLayout.setBackgroundColor(Color.rgb(255,255,255));


        switch (v.getId()){

            case R.id.street_layout:
                streetLayout.setBackgroundColor(Color.rgb(0,0,0));
                pager.setCurrentItem(0);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.popular_layout:
                popularLayout.setBackgroundColor(Color.rgb(0,0,0));
                pager.setCurrentItem(1);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.recent_layout:
                recentLayout.setBackgroundColor(Color.rgb(0,0,0));
                pager.setCurrentItem(2);
                drawer.closeDrawer(GravityCompat.START);
                break;
        }
    }



    private void  viewPagerSetting(){

        PagerFragment.mode=0;
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), false);
        pager.setAdapter(viewPagerAdapter);
        pager.setOffscreenPageLimit(1);
        tabs.setupWithViewPager(pager);
        tabs.setTabTextColors(Color.rgb(0,0,0), Color.rgb(64,197,57));


        layoutChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int current=pager.getCurrentItem();

                if(firstLayout){
                    layoutChangeBtn.setImageResource(R.drawable.layout2);
                  //  Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                  //  PagerFragment.onLayoutChangeListener.changeLayout(1);

                    PagerFragment.mode=1;
                    viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), true);
                    pager.setAdapter(viewPagerAdapter);
                    pager.setOffscreenPageLimit(1);
                    tabs.setupWithViewPager(pager);
                    tabs.setTabTextColors(Color.rgb(0,0,0), Color.rgb(64,197,57));
                    pager.setCurrentItem(current);


                }
                else{
                    layoutChangeBtn.setImageResource(R.drawable.layout1);
                  //  Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
                  //  PagerFragment.onLayoutChangeListener.changeLayout(0);

                    PagerFragment.mode=0;
                    viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), true);
                    pager.setAdapter(viewPagerAdapter);
                    pager.setOffscreenPageLimit(1);
                    tabs.setupWithViewPager(pager);
                    tabs.setTabTextColors(Color.rgb(0,0,0), Color.rgb(64,197,57));
                    pager.setCurrentItem(current);

                }

                firstLayout = !firstLayout;
            }
        });

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                streetLayout.setBackgroundColor(Color.rgb(255,255,255));
                popularLayout.setBackgroundColor(Color.rgb(255,255,255));
                recentLayout.setBackgroundColor(Color.rgb(255,255,255));

                switch (pager.getCurrentItem()){
                    case 0:
                        streetLayout.setBackgroundColor(Color.rgb(0,0,0));
                        break;
                    case 1:
                        popularLayout.setBackgroundColor(Color.rgb(0,0,0));
                        break;
                    case 2:
                        recentLayout.setBackgroundColor(Color.rgb(0,0,0));
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
