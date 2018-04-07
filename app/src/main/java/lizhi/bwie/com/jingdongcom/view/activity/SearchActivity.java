package lizhi.bwie.com.jingdongcom.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lizhi.bwie.com.jingdongcom.R;
import lizhi.bwie.com.jingdongcom.view.adapter.MyHistoryAdapter;
import lizhi.bwie.com.jingdongcom.view.adapter.MySearchRecyclerAdapter;
import lizhi.bwie.com.jingdongcom.view.fragement.HomeFragment;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView mRecyclerH;
    /**
     * 清空历史搜索
     */
    private Button mDelehistory;
    private ListView mListviewx;
    private MySearchRecyclerAdapter adapter;
    private List<String> list_recycler = new ArrayList<>();
    private List<String> list_hisitory = new ArrayList<>();
    private SharedPreferences spf;
    /**
     * 年货超级秒杀,好货低至1折
     */
    private EditText mSearchText;
    private MyHistoryAdapter historyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initList();
        historyAdapter = new MyHistoryAdapter(SearchActivity.this, list_hisitory);
        mListviewx.setAdapter(historyAdapter);
        if (spf == null) {
            mDelehistory.setVisibility(View.VISIBLE);
            mRecyclerH.setLayoutManager(new GridLayoutManager(SearchActivity.this, 5));
            adapter = new MySearchRecyclerAdapter(list_recycler, SearchActivity.this);
            mRecyclerH.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            mDelehistory.setVisibility(View.VISIBLE);
            mRecyclerH.setLayoutManager(new LinearLayoutManager(SearchActivity.this, OrientationHelper.HORIZONTAL, false));
            adapter = new MySearchRecyclerAdapter(list_recycler, SearchActivity.this);
            mRecyclerH.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


    }

    //添加集合(历史搜索记录)
    public void addlist(View view) {
        String s = mSearchText.getText().toString();
        spf.edit().putString("name", mSearchText.getText().toString()).commit();
        String name = spf.getString("name", mSearchText.getText().toString());
        list_hisitory.add(name);

        Intent intent = new Intent(SearchActivity.this, ProductListActivity.class);
        intent.putExtra("keywords", s);
        startActivity(intent);
    }

    //清除历史记录
    public void delehistory(View view) {
        SharedPreferences.Editor editor = spf.edit();
        editor.remove("search_history");
        editor.commit();
    }

    private void initView() {
        mRecyclerH = (RecyclerView) findViewById(R.id.recycler_h);
        mDelehistory = (Button) findViewById(R.id.delehistory);
        mListviewx = (ListView) findViewById(R.id.listviewx);
        mSearchText = (EditText) findViewById(R.id.search_text);
        spf = getSharedPreferences("search_history", 0);

    }

    private void initList() {
        list_recycler.add("榨汁机");
        list_recycler.add("电视柜");
        list_recycler.add("充电宝");
        list_recycler.add("手机");
        list_recycler.add("笔记本");
        list_recycler.add("电脑");
        list_recycler.add("微波炉");
    }

    //跳转到首页
    public void intenthomefragment(View view) {
        Intent intent = new Intent(SearchActivity.this, HomeFragment.class);
        startActivity(intent);
    }
}




















