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
import android.widget.Toast;

import appjam.contest.secondminiproject.R;
import appjam.contest.secondminiproject.adapter.ViewPagerAdapter;
import appjam.contest.secondminiproject.menu.PagerFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    //Navigation Setting
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;

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
