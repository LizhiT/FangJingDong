package lizhi.bwie.com.jingdongcom.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import lizhi.bwie.com.jingdongcom.R;
import lizhi.bwie.com.jingdongcom.model.bean.FenleiBean;

/**
 * author:Created by Basic on 2018/3/27.
 */

public class FenLeiAdapter extends BaseAdapter {
    private FenleiBean fenLeiBean;
    private Context context;
    private int position;

    public FenLeiAdapter(Context context, FenleiBean fenLeiBean) {
        this.context = context;
        this.fenLeiBean = fenLeiBean;
    }

    @Override
    public int getCount() {
        return fenLeiBean.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return fenLeiBean.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.fen_lei_item_layout,null);
            holder = new ViewHolder();

            holder.textView = view.findViewById(R.id.fen_lei_item_text);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        FenleiBean.DataBean dataBean = fenLeiBean.getData().get(i);
        holder.textView.setText(dataBean.getName());

        //判断
        if (position == i) {
            //设置灰色的背景 和红色文字
            view.setBackgroundColor(Color.TRANSPARENT);
            holder.textView.setTextColor(Color.RED);
        }else {
            //白色的背景和黑色的文字
            view.setBackgroundColor(Color.WHITE);
            holder.textView.setTextColor(Color.BLACK);
        }

        return view;
    }

    /**
     * 设置的是点击了条目的位置
     * @param position
     */
    public void setCurPositon(int position) {
        this.position = position;
    }

    private class ViewHolder{
        TextView textView;
    }
}
