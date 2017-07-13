package company.co.kr.project02.ui.PlaceList;

/**
 * Created by Dongjin on 2017. 7. 12..
 */

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import company.co.kr.project02.R;
import company.co.kr.project02.model.Item;

public class PlaceListFragment extends Fragment {
    private static final String TAG = "PLACE_LIST_FRAGMENT";

    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
            LinearLayoutManager.VERTICAL, false);
    private StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
    private PlaceListAdapter placeListAdapter;

    private static final int LINEAR_TYPE = 0;
    private static final int STAGGERED_TYPE = 1;
    private static int type = 0;

    private List<Item> itemList = new ArrayList<>();

    @BindView(R.id.recvPlaceList)
    RecyclerView recvPlaceList;

    @BindView(R.id.toolType)
    Toolbar toolType;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.imgBtnChangeRecyclerType)
    ImageButton imgBtnChangeRecyclerType;

    public static PlaceListFragment newInstance() {
        return new PlaceListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemList = makePlaceListItems();
        Collections.sort(itemList, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                if(item1.getDistance() > item2.getDistance()) {
                    return 1;
                } else if(item1.getDistance() < item2.getDistance()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setView();
        tabLayout.getTabAt(0).select();
        imgBtnChangeRecyclerType();
    }

    private void setView() {
        placeListAdapter = new PlaceListAdapter(getContext(), itemList,
                new PlaceListAdapter.PlaceAdapterOnClickListener() {
                    @Override
                    public void onClick(String msg) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });

        setTabLayout();
        setRecyclerView(LINEAR_TYPE);
    }

    /*
    * Set icons for each tab from @array/tab_items
    * */
    private void setTabLayout() {
        TypedArray tabIcons = getResources().obtainTypedArray(R.array.tab_items);

        for(int idx = 0; idx < tabLayout.getTabCount(); idx++) {
            tabLayout.getTabAt(idx).setIcon(tabIcons.getResourceId(idx, R.drawable.selector_tab_distance));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch(position) {
                    case 0:
                        Log.d(TAG, "tab01");
                        Collections.sort(itemList, new Comparator<Item>() {
                            @Override
                            public int compare(Item item1, Item item2) {
                                if(item1.getDistance() > item2.getDistance()) {
                                    return 1;
                                } else if(item1.getDistance() < item2.getDistance()) {
                                    return -1;
                                }
                                return 0;
                            }
                         });
                        break;
                    case 1:
                        Log.d(TAG, "tab02");
                        Collections.sort(itemList, new Comparator<Item>() {
                            @Override
                            public int compare(Item item1, Item item2) {
                                if(item1.getLike() < item2.getLike()) {
                                    return 1;
                                } else if(item1.getLike() > item2.getLike()) {
                                    return -1;
                                }
                                return 0;
                            }
                        });
                        break;
                    case 2:
                        Log.d(TAG, "tab03");
                        Collections.sort(itemList, new Comparator<Item>() {
                            @Override
                            public int compare(Item item1, Item item2) {
                                if(item1.getDate() < item2.getDate()) {
                                    return 1;
                                } else if(item1.getDate() > item2.getDate()) {
                                    return -1;
                                }
                                return 0;
                            }
                        });
                        Collections.reverse(itemList);
                        break;
                }
                placeListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setRecyclerView(int type) {
        if(type == LINEAR_TYPE) {
            recvPlaceList.setLayoutManager(linearLayoutManager);
        }
        else {
            recvPlaceList.setLayoutManager(staggeredGridLayoutManager);
        }

        recvPlaceList.setAdapter(placeListAdapter);
    }

    /*
    * Action to change recycler view type
    * */
    @OnClick(R.id.imgBtnChangeRecyclerType)
    public void imgBtnChangeRecyclerType() {
        imgBtnChangeRecyclerType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                type = (type+1) % 2;
                setRecyclerView(getRecyclerItemType(type));
            }
        });
    }

    private int getRecyclerItemType(int type) {
        if(type == 0)
            return LINEAR_TYPE;
        else if(type == 1)
            return STAGGERED_TYPE;
        return LINEAR_TYPE;
    }

    /*
    * Make item list
    * */
    private List<Item> makePlaceListItems() {

        itemList.add(new Item(R.drawable.rabbit, "토끼정", "크림카레 우동이 맛있고 데이트 장소로 알맞은 곳입니다.",
                false, 3, 100, 75));
        itemList.add(new Item(R.drawable.food1, "육회육회", "육회는 진리", false, 4, 1, 26));
        itemList.add(new Item(R.drawable.samgyup, "동네 삼겹살", "육즙이 넘치는 삼겹살 맛집입니다. 전복과 함께 환상적인 조화를 맛보세요. " +
                "안산에 위치해 있습니다.", false, 5, 33, 77));
        itemList.add(new Item(R.drawable.food2, "족발족발", "족발도 좋지", false, 6, 34, 2));
        itemList.add(new Item(R.drawable.food3, "서대회집서대회서대회서대회서대회", "여수 서대회 맛있음", false, 2, 45, 8));
        itemList.add(new Item(R.drawable.food4, "고요남", "고인돌 고기", false, 30, 56, 6));
        itemList.add(new Item(R.drawable.food5, "냉면집", "이렇게 더운 여름엔 시원한 물냉면이 최고. 100% 고기육수만 사용하고 ", false, 7, 600, 10));
        itemList.add(new Item(R.drawable.shrimp, "새우집", "꼬리에 좋은 성분이 가득한 새우! 꼬리는 제거하고 살만 쏙 빼드시나요? 이제는 새우 꼬리까지 드세요. " +
                "씹을수록 고소한 맛이 일품이랍니다.고혈압 예방, 성장 발육 (칼슘과 타우린이 풍부하게 들어 있어 고혈압 예방과 성장 발육에 효과적이고 키토산은 혈액 내 콜레스테롤을 낮추는 역할을 한다.)", false, 1, 56, 6));
        return itemList;
    }
}