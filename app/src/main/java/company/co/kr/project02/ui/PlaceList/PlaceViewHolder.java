package company.co.kr.project02.ui.PlaceList;

/**
 * Created by Dongjin on 2017. 7. 12..
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import company.co.kr.project02.R;
import company.co.kr.project02.model.Item;

public class PlaceViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "PlaceViewHolder";

    @BindView(R.id.imgView_pic)
    ImageView imgView_pic;

    @BindView(R.id.txtItem_title)
    TextView txtItem_title;

    @BindView(R.id.imgBtn_chk)
    ImageButton imgBtn_chk;

    @BindView(R.id.txtItem_content)
    TextView txtItem_content;

    private Context mContext;
    private PlaceListAdapter.PlaceAdapterOnClickListener mClickListener;

    public PlaceViewHolder(View view, Context mContext) {
        super(view);
        this.mContext = mContext;

        ButterKnife.bind(this, view);
    }

    public void bind(final Item item, PlaceListAdapter.PlaceAdapterOnClickListener handler) {
        Log.i(TAG, "bind 호출 : " + item.getTitle());
        this.mClickListener = handler;

        Picasso
                .with(mContext)
                .load(item.getImgSrc())
                .resize(1000,600)
                .centerCrop()
                .into(imgView_pic);

        txtItem_title.setText(item.getTitle());
        txtItem_title.setSelected(true);
        imgBtn_chk.setSelected(item.isCheck());
        txtItem_content.setText(item.getContent());

        imgBtn_chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                item.setCheck(!item.isCheck());
                Log.d(TAG, item.toString());

                mClickListener.onClick("체크 클릭");
            }
        });

    }
}
