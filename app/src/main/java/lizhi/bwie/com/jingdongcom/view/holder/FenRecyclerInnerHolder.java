package lizhi.bwie.com.jingdongcom.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import lizhi.bwie.com.jingdongcom.R;

/**
 * author:Created by Basic on 2018/3/27.
 */

public class FenRecyclerInnerHolder extends RecyclerView.ViewHolder {

    public ImageView recycler_innner_image;
    public TextView recycler_inner_text;

    public FenRecyclerInnerHolder(View itemView) {
        super(itemView);

        recycler_innner_image = itemView.findViewById(R.id.recycler_innner_image);
        recycler_inner_text = itemView.findViewById(R.id.recycler_inner_text);

    }
}
