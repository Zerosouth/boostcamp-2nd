package project.android.mini2.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import project.android.mini2.R;

/**
 * Created by qkrqh on 2017-07-11.
 */

public class ViewHolder extends RecyclerView.ViewHolder{

    public ImageView contentImg, checkView;
    public TextView name, content;

    public ViewHolder(View view) {
        super(view);
        contentImg = (ImageView)view.findViewById(R.id.contentImg);
        checkView = (ImageView)view.findViewById(R.id.check);
        name = (TextView)view.findViewById(R.id.name);
        content = (TextView)view.findViewById(R.id.content);
    }


}
