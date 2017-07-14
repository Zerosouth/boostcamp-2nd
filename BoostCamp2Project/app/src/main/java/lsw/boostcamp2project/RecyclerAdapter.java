package lsw.boostcamp2project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by lsw38 on 2017-07-11.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    ArrayList<Item> items;
    Context mContext;
    int ArrayType;

    TabPagerAdapter tabPagerAdapter;

    public RecyclerAdapter(Context mContext, ArrayList items, int arrayType, TabPagerAdapter tabPagerAdapter) {
        this.items = items;
        this.mContext = mContext;
        this.ArrayType = arrayType;
        this.tabPagerAdapter = tabPagerAdapter;
        sorting(arrayType);


    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i){
        viewHolder.content_title.setText(items.get(i).content_title);
        viewHolder.item_Image.setImageResource(items.get(i).image);
        viewHolder.context.setText(items.get(i).context);
        if(items.get(i).getCheck() == 0){
            viewHolder.check_ic.setImageResource(R.drawable.ic_none_check);

        }else{
            viewHolder.check_ic.setImageResource(R.drawable.ic_checked);
        }

        viewHolder.check_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (items.get(i).getCheck() == 0){
                    viewHolder.check_ic.setImageResource(R.drawable.ic_none_check);
                    items.get(i).setCheck(1);
                    tabPagerAdapter.notifyDataSetChanged();


                }else{
                    viewHolder.check_ic.setImageResource(R.drawable.ic_checked);
                    items.get(i).setCheck(0);
                    tabPagerAdapter.notifyDataSetChanged();

                }
            }
        });



    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView item_Image;
        public TextView content_title;
        public ImageButton check_ic;
        public TextView context;
        public ViewHolder(View itemView){
            super(itemView);
            item_Image = (ImageView) itemView.findViewById(R.id.item_image);
            content_title = (TextView) itemView.findViewById(R.id.content_title);
            check_ic = (ImageButton) itemView.findViewById(R.id.check_ic);
            context = (TextView) itemView.findViewById(R.id.context);

        }
    }

    @Override public int getItemCount(){
        return items.size();
    }

    Comparator<Item> compare_distance = new Comparator<Item>() {
        @Override
        public int compare(Item item1, Item item2) {
            if(item1.getDistance() == item2.getDistance()){
                return 0;
            }else if( item1.getDistance() > item2.getDistance()){
                return 1;
            }else{
                return -1;
            }
        }
    };

    Comparator<Item> compare_recent = new Comparator<Item>() {
        @Override
        public int compare(Item item1, Item item2) {
            if(item1.getUpdate() == item2.getUpdate()){
                return 0;
            }else if( item1.getUpdate() > item2.getUpdate()){
                return 1;
            }else{
                return -1;
            }
        }
    };
    Comparator<Item> compare_Popularity = new Comparator<Item>() {
        @Override
        public int compare(Item item1, Item item2) {
            if(item1.getPopularity() == item2.getPopularity()){
                return 0;
            }else if( item1.getPopularity() > item2.getPopularity()){
                return 1;
            }else{
                return -1;
            }
        }
    };
    public void sorting( int arrayType){
        if(arrayType == 0) {
            Collections.sort(items,  compare_distance);
        }else if(arrayType == 1){
            Collections.sort(items, compare_Popularity);
        }else if(arrayType== 2){
            Collections.sort(items, compare_recent);
        }
    }
}
