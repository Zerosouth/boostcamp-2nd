package lsw.boostcamp2project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by lsw38 on 2017-07-11.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Item> items;
    int type = 0;
    DistanceTabFragment distanceTabFragment;
    PopularityTabFragment popularityTabFragment;
    RecentTabFragment recentTabFragment;
    ArrayList<Item> Ditems;
    ArrayList<Item> Pitems;
    ArrayList<Item> Ritems;

    public void setLayout(int Vtype){
        this.type = Vtype;
    }

    public TabPagerAdapter(FragmentManager fm, ArrayList<Item> items){

        super(fm);
        this.items = items;
        Ditems = (ArrayList<Item>) items.clone();
        Pitems = (ArrayList<Item>) items.clone();
        Ritems = (ArrayList<Item>) items.clone();
        distanceTabFragment = DistanceTabFragment.newInstance(Ditems);
        popularityTabFragment = PopularityTabFragment.newInstance(Pitems);
        recentTabFragment = RecentTabFragment.newInstance(Ritems);
    }

    @Override
    public Fragment getItem(int position){
        switch(position){

            case 0:
                //ArrayList<Item> Ditems = (ArrayList<Item>) items.clone();
                //distanceTabFragment.setItems(Ditems);
                return  distanceTabFragment;


            case 1:
               // ArrayList<Item> Pitems = (ArrayList<Item>) items.clone();
                //popularityTabFragment.setItems(Pitems);
                return popularityTabFragment;

            case 2:
               // ArrayList<Item> Ritems = (ArrayList<Item>) items.clone();
                //recentTabFragment.setItems(Ritems);
                return recentTabFragment;

            default :
                return null;
        }
    }

    @Override
    public int getCount(){
        return 3;
    }

    @Override
    public int getItemPosition(Object item) {

        return POSITION_NONE;   // notifyDataSetChanged


    }


    public DistanceTabFragment getDistanceTabFragment(){

        return distanceTabFragment;

    }

    public PopularityTabFragment getPopularityTabFragment(){

        return popularityTabFragment;

    }
    public RecentTabFragment getRecentTabFragment(){

        return recentTabFragment;

    }



}
