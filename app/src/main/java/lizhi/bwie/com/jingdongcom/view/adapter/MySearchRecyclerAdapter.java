package lizhi.bwie.com.jingdongcom.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lizhi.bwie.com.jingdongcom.R;
import lizhi.bwie.com.jingdongcom.view.holder.MySearchHolder;

/**
 * author:Created by Basic on 2018/3/26.
 */

public class MySearchRecyclerAdapter extends RecyclerView.Adapter<MySearchHolder>{

    private List<String> list;
    private Context context;

    public MySearchRecyclerAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MySearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {

        View view = View.inflate(context, R.layout.search_item_recycler, null);
        MySearchHolder holder = new MySearchHolder(view);
        holder.recycler_item_text.setText(list.get(position));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MySearchHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
