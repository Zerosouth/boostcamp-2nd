package lsw.boostcamp2project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularityTabFragment extends Fragment {
    ArrayList<Item> items;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    TabPagerAdapter tabPagerAdapter;
    int type = 0;
    public PopularityTabFragment() {
        // Required empty public constructor
    }
    public static PopularityTabFragment newInstance(ArrayList<Item> items) {
        Bundle args = new Bundle();
        PopularityTabFragment fragment = new PopularityTabFragment();
        fragment.setArguments(args);
        fragment.items = items;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_popularity_tab, container, false);

        return v;
    }

    public void setItems(ArrayList<Item> items){
        this.items = items;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view_tab_2);
        if(type == 0){
            LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(lm);
        }else{
            StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(2,1);
            recyclerView.setLayoutManager(lm);
        }
        this.adapter = new RecyclerAdapter(getActivity(), items , 1, tabPagerAdapter);
        recyclerView.setAdapter(adapter);



    }

    public TabPagerAdapter getTabPagerAdapter() {
        return tabPagerAdapter;
    }

    public void setTabPagerAdapter(TabPagerAdapter tabPagerAdapter) {
        this.tabPagerAdapter = tabPagerAdapter;
    }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser)
//    {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser)
//        {
////실제로 사용자 눈에 보이는경우
//
//        }
//        else
//        {
//// 전페이지에서 Preload 될 경우
//        }
//    }

    public void setLayout(int Vtype){
        if(Vtype == 0){

            this.type = 1;
        }else{

            this.type = 0;
        }
    }
}
