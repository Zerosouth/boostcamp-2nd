package appjam.contest.secondminiproject.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

import appjam.contest.secondminiproject.menu.PagerFragment;

/**
 * Created by jeongdahun on 2017. 7. 13..
 */

//Pager Adapter
public class ViewPagerAdapter extends FragmentStatePagerAdapter
{
    boolean saveMode=false;

    public ViewPagerAdapter(FragmentManager fm, boolean saveMode) {
        super(fm);
        this.saveMode=saveMode;
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

        }
        return "TEST";
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch(position){
            case 0:
                return PagerFragment.newInstance(1,saveMode);
            case 1:
                return PagerFragment.newInstance(2,saveMode);
            case 2:
                return PagerFragment.newInstance(3,saveMode);
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 3;
    }


}