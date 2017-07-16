package com.example.doublejk.project2;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by doublejk on 2017-07-12.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private List<RestaurantItem> restaurantList;

    public RecyclerAdapter(Context context, List<RestaurantItem> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //뷰 전개
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override //데이터 변경
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.cardImg.setImageResource(restaurantList.get(position).getCardImg());
        holder.title.setText(restaurantList.get(position).getTitle());
        holder.title.setPaintFlags(holder.title.getPaintFlags()| Paint.FAKE_BOLD_TEXT_FLAG);
        holder.preview.setText(restaurantList.get(position).getPreview());
        holder.cardCheck.setChecked(restaurantList.get(position).isChecked());

        holder.cardCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    restaurantList.get(position).setChecked(true);
                else
                    restaurantList.get(position).setChecked(false);
            }
        });
    }


    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    //위젯연결
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView cardImg;
        public CheckBox cardCheck;
        public TextView title, preview;
        public ViewHolder(View itemView) {
            super(itemView);
            cardImg = (ImageView) itemView.findViewById(R.id.cardImg);
            cardCheck = (CheckBox) itemView.findViewById(R.id.cardCheck);
            title = (TextView) itemView.findViewById(R.id.cardTitle);
            preview = (TextView) itemView.findViewById(R.id.cardPreview);

        }
    }
}
