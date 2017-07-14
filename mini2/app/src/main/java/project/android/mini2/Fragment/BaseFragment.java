package project.android.mini2.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import project.android.mini2.Adapter.ItemAdapter;
import project.android.mini2.Data.Item;
import project.android.mini2.Main.MainActivity;
import project.android.mini2.R;

import static project.android.mini2.Main.MainActivity.items;

/**
 * Created by qkrqh on 2017-07-13.
 */

public abstract class BaseFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ImageView menu;

    StaggeredGridLayoutManager st;

    static final int linear = 1;
    static final int grid = 0;
    static int state = linear;

    public BaseFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment, container, false);
        menu = (ImageView)getActivity().findViewById(R.id.menu);
        menu.setOnClickListener(mClickListener);

        context = getActivity();
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler1);

        setRecyclerView(state);

        return view;
    }

    public void onResume(){
        super.onResume();

        sorting();
    }

    public static android.support.v4.app.Fragment newInstance(int position){

        switch (position){
            case 0 :
                DistanceFragment tab1 = new DistanceFragment();
                return tab1;
            case 1 :
                PopularityFragment tab2 = new PopularityFragment();
                return tab2;
            case 2 :
                RecentlyFragment tab3 = new RecentlyFragment();
                return tab3;
        }
        return null;
    }

    void setRecyclerView(int state){

        if(state == linear){
            st = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
          }
        else {
            st = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        }

        recyclerView.setLayoutManager(st);
        adapter = new ItemAdapter(items,context,R.layout.item_layout);
        recyclerView.setAdapter(adapter);
    }

    public static boolean noti(int position){
        Item i = (Item)MainActivity.items.get(position);
        if(i.getCheck() == true)
            i.setCheck(false);
        else
            i.setCheck(true);
        MainActivity.items.set(position,i);
        return i.getCheck();
    }

    ImageView.OnClickListener mClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.menu :
                    if(state == linear){
                        menu.setImageResource(R.drawable.menu_icon1);
                        setRecyclerView(grid);
                        state = grid;
                    }
                    else{
                        menu.setImageResource(R.drawable.menu_icon2);
                        setRecyclerView(linear);
                        state=linear;
                    }
                    break;
            }
        }
    };

    abstract void sorting();

}
