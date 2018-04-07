package lizhi.bwie.com.jingdongcom.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import lizhi.bwie.com.jingdongcom.R;

/**
 * author:Created by Basic on 2018/3/15.
 */

public class MiaoShaHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public MiaoShaHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.miaosha_image);

    }
}
