package appjam.contest.secondminiproject.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import appjam.contest.secondminiproject.R;
import appjam.contest.secondminiproject.adapter.FoodStoreRecyclerViewAdapter;
import appjam.contest.secondminiproject.listener.OnCheckedListener;
import appjam.contest.secondminiproject.listener.OnLayoutChangeListener;
import appjam.contest.secondminiproject.main.MainActivity;
import appjam.contest.secondminiproject.model.FoodStore;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jeongdahun on 2017. 7. 13..
 */

public class PagerFragment extends Fragment implements OnLayoutChangeListener{

    public static ArrayList<FoodStore> arrayList=new ArrayList<FoodStore>();

    public static ArrayList<FoodStore> street_arrayList=new ArrayList<FoodStore>();
    public static ArrayList<FoodStore> popular_arrayList=new ArrayList<FoodStore>();
    public static ArrayList<FoodStore> recent_arrayList=new ArrayList<FoodStore>();

    
    LinearLayout layout;

    RecyclerView foodstoreRecylcerView;
    FoodStoreRecyclerViewAdapter foodStoreRecyclerViewAdapter;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    public static int mode=0;
    int tabMode;
    
    public static OnLayoutChangeListener onLayoutChangeListener;

    public PagerFragment(){

    }

    public static PagerFragment newInstance(int tabMode, boolean saveMode){

        PagerFragment pagerFragment = new PagerFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("tab", tabMode);
        pagerFragment.setArguments(bundle);


        if(saveMode)
            return pagerFragment;


        FoodStore foodStore1=new FoodStore(R.drawable.foodstore3,"오야오야" ,"오야오야 완전 오야오야에요!!!",121,100);
        FoodStore foodStore2=new FoodStore(R.drawable.foodstore1,"해우소" ,"꿀막걸리가 인기메뉴고 맛있는 음식을 저렴하게 즐길 수 있는 해우소입니다",70,90);
        FoodStore foodStore3=new FoodStore(R.drawable.foodstore2, "삼거리동치미막국수","막국수를 비빔으로 먹을지 물로 먹을지 고객이 만들어 먹는 삼교리동치미막국수입니다.",66,20);
        FoodStore foodStore4=new FoodStore(R.drawable.foodstore4, "까치둥지" ,"까치둥지 완전 존맛입니다!!!!!!!알 완전 맛있습니다!!",1000,20);

        switch (tabMode){
            case 1:
                street_arrayList=new ArrayList<FoodStore>();

                street_arrayList.add(foodStore1);
                street_arrayList.add(foodStore2);
                street_arrayList.add(foodStore3);
                street_arrayList.add(foodStore4);

                break;
            case 2:
                popular_arrayList=new ArrayList<FoodStore>();

                popular_arrayList.add(foodStore1);
                popular_arrayList.add(foodStore2);
                popular_arrayList.add(foodStore3);
                popular_arrayList.add(foodStore4);


                for(int i=0;i<popular_arrayList.size();i++){
                    int max=i;
                    for(int j=i+1;j<popular_arrayList.size();j++){
                        if(popular_arrayList.get(max).getLikeCount() < popular_arrayList.get(j).getLikeCount()){
                            max=j;
                        }
                    }
                    FoodStore tmp = popular_arrayList.get(i);
                    FoodStore tmp2 = popular_arrayList.get(max);

                    popular_arrayList.remove(i);
                    popular_arrayList.add(i, tmp2);
                    popular_arrayList.remove(max);
                    popular_arrayList.add(max, tmp);
                }
                break;

            case 3:
                recent_arrayList=new ArrayList<FoodStore>();

                recent_arrayList.add(foodStore1);
                recent_arrayList.add(foodStore2);
                recent_arrayList.add(foodStore3);
                recent_arrayList.add(foodStore4);

                for(int i=0;i<recent_arrayList.size();i++){
                    int min=i;
                    for(int j=i+1;j<recent_arrayList.size();j++){
                        if(recent_arrayList.get(min).getOldCount() > recent_arrayList.get(j).getOldCount()){
                            min=j;
                        }
                    }
                    FoodStore tmp = recent_arrayList.get(i);
                    FoodStore tmp2 = recent_arrayList.get(min);

                    recent_arrayList.remove(i);
                    recent_arrayList.add(i, tmp2);
                    recent_arrayList.remove(min);
                    recent_arrayList.add(min, tmp);
                }
                break;
        }




        return pagerFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        layout = (LinearLayout) inflater.inflate(R.layout.pager_fragment, container, false);
        ButterKnife.bind(getActivity());

        initSetting();
        recyclerviewSetting();

        return layout;
    }

    private void initSetting(){
        Bundle bundle=getArguments();
        tabMode=bundle.getInt("tab");
    }

    private void recyclerviewSetting(){

        OnCheckedListener onCheckedListener=new OnCheckedListener() {
            @Override
            public void setCheck(int position) {

                switch (tabMode){
                    case 1:
                        street_arrayList.get(position).setCheck();
                        break;
                    case 2:
                        popular_arrayList.get(position).setCheck();
                        break;
                    case 3:
                        recent_arrayList.get(position).setCheck();
                        break;
                }

            }

            @Override
            public boolean getCheck(int position) {
                switch (tabMode){
                    case 1:
                        return street_arrayList.get(position).getCheck();
                    case 2:
                        return popular_arrayList.get(position).getCheck();
                    case 3:
                        return recent_arrayList.get(position).getCheck();

                    default:
                        return false;
                }

            }


        };

        foodstoreRecylcerView=(RecyclerView)layout.findViewById(R.id.foodstoreRecylcerView);
        switch (tabMode){
            case 1:
                foodStoreRecyclerViewAdapter=new FoodStoreRecyclerViewAdapter(getContext(), onCheckedListener, street_arrayList);
                break;
            case 2:
                foodStoreRecyclerViewAdapter=new FoodStoreRecyclerViewAdapter(getContext(), onCheckedListener, popular_arrayList);
                break;
            case 3:
                foodStoreRecyclerViewAdapter=new FoodStoreRecyclerViewAdapter(getContext(), onCheckedListener, recent_arrayList);
                break;
        }

        if(mode==0){
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            foodstoreRecylcerView.setLayoutManager(staggeredGridLayoutManager);
        } else{
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            foodstoreRecylcerView.setLayoutManager(staggeredGridLayoutManager);
        }

        foodstoreRecylcerView.setAdapter(foodStoreRecyclerViewAdapter);


        //onLayoutChangeListener=(OnLayoutChangeListener)this;
    }


    @Override
    public void changeLayout(int layout) {
        Toast.makeText(getContext(),String.valueOf(layout)+"레이아웃",Toast.LENGTH_SHORT).show();
        if(layout==0){
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            foodstoreRecylcerView.setLayoutManager(staggeredGridLayoutManager);
        }else{
         //   linearLayoutManager=new LinearLayoutManager(getContext());
         //   foodstoreRecylcerView.setLayoutManager(linearLayoutManager);
        }
    }
}
