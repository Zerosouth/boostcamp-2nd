package company.co.kr.project02.ui.PlaceList;

/**
 * Created by Dongjin on 2017. 7. 12..
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import company.co.kr.project02.R;
import company.co.kr.project02.model.Item;

public class PlaceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PlaceAdapterOnClickListener mClickListener;
    private Context mContext;
    private List<Item> itemList = new ArrayList<>();

     /*
     * The interface that receives onClick messages.
     * */
    public interface PlaceAdapterOnClickListener {
        void onClick(String msg);
    }

    public PlaceListAdapter(Context mContext, List<Item> mItemList, PlaceAdapterOnClickListener mClickListener) {
        this.mContext = mContext;
        this.itemList = mItemList;
        this.mClickListener = mClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlaceViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_place, parent, false), mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PlaceViewHolder) holder).bind(itemList.get(position), mClickListener);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
