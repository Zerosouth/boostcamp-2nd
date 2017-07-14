package com.example.doublejk.project2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by doublejk on 2017-07-11.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;
    private int sortType;
    private boolean layoutType;
    public ViewPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        tabCount = numberOfTabs;
    }
    public void setLayoutType(boolean type) {
        this.layoutType = type;
    }
    public void setSortType(int sortType) {
        this.sortType = sortType;
    }
    @Override
    public Fragment getItem(int position) {
        //Log.d("겟아이템", "" + position + " " + layoutType + " " + sortType);
        switch (position) {
            case 0:
            case 1:
            case 2:
                return RestaurantFragment.newInstance(layoutType, sortType);
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "거리순";
            case 1:
                return "인기순";
            case 2:
                return "최신순";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
