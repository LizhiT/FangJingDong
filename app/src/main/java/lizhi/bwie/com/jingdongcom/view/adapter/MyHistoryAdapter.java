package lizhi.bwie.com.jingdongcom.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import lizhi.bwie.com.jingdongcom.R;

/**
 * author:Created by Basic on 2018/3/26.
 */

public class MyHistoryAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public MyHistoryAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = View.inflate(context, R.layout.history_item, null);
        TextView textView = (TextView) view1.findViewById(R.id.history_text);
        textView.setText(list.get(i));
        return view1;
    }
}
