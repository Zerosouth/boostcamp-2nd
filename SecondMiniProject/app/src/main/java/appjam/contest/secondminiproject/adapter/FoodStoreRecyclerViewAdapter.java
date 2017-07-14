package appjam.contest.secondminiproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import appjam.contest.secondminiproject.R;
import appjam.contest.secondminiproject.listener.OnCheckedListener;
import appjam.contest.secondminiproject.model.FoodStore;
import appjam.contest.secondminiproject.viewholder.FoodStoreViewHolder;

/**
 * Created by jeongdahun on 2017. 7. 13..
 */

public class FoodStoreRecyclerViewAdapter extends RecyclerView.Adapter<FoodStoreViewHolder> {

    private Context context;
    private ArrayList<FoodStore> items;
    private OnCheckedListener onCheckedListener;

    public FoodStoreRecyclerViewAdapter(Context context, OnCheckedListener onCheckedListener, ArrayList<FoodStore> items){
        this.context=context;
        this.items=items;
        this.onCheckedListener=onCheckedListener;
    }

    @Override
    public FoodStoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodstore_viewholder, parent, false);
        return new FoodStoreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FoodStoreViewHolder holder, final int position) {
        final FoodStore item=items.get(position);


        DisplayMetrics dm=context.getResources().getDisplayMetrics();
        int width=dm.widthPixels;
        int height=dm.heightPixels;

        Glide.with(context).load(item.getImageId()).override(width/2,height/3).into(holder.foodstore_image);
        holder.foodstore_name.setText(item.getName());

        if(onCheckedListener.getCheck(position))
            holder.checkBtn.setImageResource(R.drawable.on);
        else
            holder.checkBtn.setImageResource(R.drawable.off);

        holder.checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onCheckedListener.getCheck(position)){
                    holder.checkBtn.setImageResource(R.drawable.off);
                }
                else{
                    holder.checkBtn.setImageResource(R.drawable.on);
                }

                onCheckedListener.setCheck(position);
            }
        });
        holder.foodstore_explain.setText(item.getExplain());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
