package lizhi.bwie.com.jingdongcom.view.fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import lizhi.bwie.com.jingdongcom.R;
import lizhi.bwie.com.jingdongcom.model.bean.FenleiBean;
import lizhi.bwie.com.jingdongcom.model.bean.HomeBean;
import lizhi.bwie.com.jingdongcom.presenter.PresenterHome;
import lizhi.bwie.com.jingdongcom.util.ApiUtil;
import lizhi.bwie.com.jingdongcom.util.ChenJinUtil;
import lizhi.bwie.com.jingdongcom.view.adapter.FenLeiAdapter;
import lizhi.bwie.com.jingdongcom.view.inter.IHomeV;


public class CLassifyFragment extends Fragment implements IHomeV {
    private ListView fen_lei_list_view;
    private PresenterHome fragmentHomeP;
    private FenLeiAdapter fenLeiAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify, null);

        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initChenJin();

        //获取左边listView展示的数据
        fragmentHomeP = new PresenterHome(this);

        fragmentHomeP.getFenLeiData(ApiUtil.FEN_LEI_URL);

    }

    @Override
    public void onSuccess(HomeBean homeBean) {

    }

    @Override
    public void onSuccessFenlei(final FenleiBean fenLeiBean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //给listView设置适配器
                fenLeiAdapter = new FenLeiAdapter(getActivity(), fenLeiBean);
                fen_lei_list_view.setAdapter(fenLeiAdapter);

                //默认显示京东超市右边对应的数据
                FragmentFenLeiRight fragmentFenLeiRight = FragmentFenLeiRight.getInstance(fenLeiBean.getData().get(0).getCid());

                getChildFragmentManager().beginTransaction().replace(R.id.fen_lei_frame, fragmentFenLeiRight).commit();


                //条目点击事件
                fen_lei_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //设置适配器当前位置的方法
                        fenLeiAdapter.setCurPositon(i);
                        //刷新适配器...getView重新执行
                        fenLeiAdapter.notifyDataSetChanged();
                        //滚动到指定的位置,,,第一个参数是滚动哪一个条目,,,滚动到哪个位置/偏移量
                        fen_lei_list_view.smoothScrollToPositionFromTop(i, (adapterView.getHeight() - view.getHeight()) / 2);

                        //使用fragment替换右边frameLayout....fragment之间的传值
                        FragmentFenLeiRight fragmentFenLeiRight = FragmentFenLeiRight.getInstance(fenLeiBean.getData().get(i).getCid());

                        getChildFragmentManager().beginTransaction().replace(R.id.fen_lei_frame, fragmentFenLeiRight).commit();

                    }
                });
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            initChenJin();
        }
    }

    private void initChenJin() {
        ChenJinUtil.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimaryDark));
    }

    private void initView(View view) {
        fen_lei_list_view = (ListView) view.findViewById(R.id.fen_lei_list_view);
    }
}
