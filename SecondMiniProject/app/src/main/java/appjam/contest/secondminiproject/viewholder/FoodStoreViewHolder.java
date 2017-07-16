package appjam.contest.secondminiproject.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import appjam.contest.secondminiproject.R;
import butterknife.BindView;
import butterknife.BindViews;

/**
 * Created by jeongdahun on 2017. 7. 13..
 */

public class FoodStoreViewHolder extends RecyclerView.ViewHolder {

    //@BindView(R.id.foodstore_image) public ImageView foodstore_image;
    //@BindView(R.id.foodstore_explain) public TextView foodstore_explain;

    public ImageView foodstore_image;
    public TextView foodstore_name;
    public ImageView checkBtn;
    public TextView foodstore_explain;

    public FoodStoreViewHolder(View itemView) {
        super(itemView);

        foodstore_image=(ImageView)itemView.findViewById(R.id.foodstore_image);
        foodstore_name=(TextView)itemView.findViewById(R.id.foodstore_name);
        checkBtn=(ImageView)itemView.findViewById(R.id.checkBtn);
        foodstore_explain=(TextView)itemView.findViewById(R.id.foodstore_explain);
    }
}
