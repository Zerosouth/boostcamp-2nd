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
public class RecentTabFragment extends Fragment {

    ArrayList<Item> items;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    TabPagerAdapter tabPagerAdapter;
    int type = 0;

    public RecentTabFragment() {
        // Required empty public constructor
    }
    public static RecentTabFragment newInstance(ArrayList<Item> items) {
        Bundle args = new Bundle();
        RecentTabFragment fragment = new RecentTabFragment();
        fragment.setArguments(args);
        fragment.items = items;
        return fragment;
    }

    public void setItems(ArrayList<Item> items){
        this.items = items;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recent_tab, container, false);

        return v;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Collections.sort(items, compare_recent);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view_tab_3);
        if(type == 0){
            LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(lm);
        }else{
            StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(2,1);
            recyclerView.setLayoutManager(lm);
        }
        this.adapter = new RecyclerAdapter(getActivity(), items, 2, tabPagerAdapter);
        recyclerView.setAdapter(adapter);


    }

    public TabPagerAdapter getTabPagerAdapter() {
        return tabPagerAdapter;
    }

    public void setTabPagerAdapter(TabPagerAdapter tabPagerAdapter) {
        this.tabPagerAdapter = tabPagerAdapter;
    }

    public void setLayout(int Vtype){
        if(Vtype == 0){
            this.type = 1;
        }else{

            this.type = 0;
        }
    }
}
