package lizhi.bwie.com.jingdongcom.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lizhi.bwie.com.jingdongcom.R;

/**
 * author:Created by Basic on 2018/3/26.
 */

public class MySearchHolder extends RecyclerView.ViewHolder {

    public TextView recycler_item_text;

    public MySearchHolder(View itemView) {
        super(itemView);

        recycler_item_text = (TextView) itemView.findViewById(R.id.search_item_recycler);
    }
}
