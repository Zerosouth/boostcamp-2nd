package com.example.kwy2868.boostcamp_2nd.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.kwy2868.boostcamp_2nd.Fragment.BaseFragment;
import com.example.kwy2868.boostcamp_2nd.Fragment.DistanceFragment;
import com.example.kwy2868.boostcamp_2nd.Fragment.LatestFragment;
import com.example.kwy2868.boostcamp_2nd.Fragment.PopularityFragment;
import com.example.kwy2868.boostcamp_2nd.Model.DataForTest;
import com.example.kwy2868.boostcamp_2nd.Model.Restaurant;

import java.util.ArrayList;

/**
 * Created by kwy2868 on 2017-07-11.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private boolean showType;
    // 리스트 정보를 가지고 있어야 프래그먼트에 전달해줄듯.
    private static ArrayList list = new DataForTest().getList();

    // 3짜리 만들어온다.
    // 이렇게 배열을 통해서 가져와야 뷰페이저에서 적용이 잘 된다.
    private BaseFragment[] fragmentArray;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public BaseFragment[] getFragmentArray() {
        return fragmentArray;
    }

    // 원래는 이걸로 다시 프래그먼트 배열 만들어서 해결하려 했음.
    // 근데 맨날 터지고 해결이 어려워서 야매 방식 사용.
    // 이거 안써도 되는데?
//    public void setFragmentArray(BaseFragment[] fragmentArray) {
//        this.fragmentArray = fragmentArray;
//    }

    public void setShowType(boolean showType) {
        this.showType = showType;
    }

    public void setFragmentArraySize(int size) {
        fragmentArray = new BaseFragment[size];
    }

    // 프래그먼트마다 다르게 해줘야할듯? position 값에 따라.
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                DistanceFragment distanceFragment = DistanceFragment.newInstance(showType);
//                fragmentArray[position] = distanceFragment;
                return distanceFragment;
            case 1:
                PopularityFragment popularityFragment = PopularityFragment.newInstance(showType);
//                fragmentArray[position] = popularityFragment;
                return popularityFragment;
            case 2:
                LatestFragment latestFragment = LatestFragment.newInstance(showType);
//                fragmentArray[position] = latestFragment;
                return latestFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    // 요거하면 아이템이 사라진줄 알고 다시 다뿌려준다.
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    // 이제 리스트에서 체크박스를 찾자.
    public void NotifyCheckBox(Restaurant restaurant){
        Log.d("체크한 레스토랑 이름", "이름 : " + restaurant.getName());
        // 찾자.
        for(int i=0; i<list.size(); i++){
//            Restaurant compareTo = (Restaurant)distanceFragment.getList().get(i);
            Restaurant compareTo = (Restaurant) list.get(i);
            Log.d("리스트에서 꺼내온 레스토랑 이름", "이름 : " + compareTo.getName());
            // 리스트에서 꺼내온 것이 체크한 레스토랑과 같으면.
            if(compareTo.equals(restaurant)){
                compareTo.setIs_checked(!compareTo.is_checked());
                Log.d("체크 반대로 해주자", "이름 : " + compareTo.getName());
                notifyDataSetChanged();
                break;
            }
        }
    }
}
