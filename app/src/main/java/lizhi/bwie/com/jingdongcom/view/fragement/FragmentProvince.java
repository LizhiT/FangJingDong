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

import java.util.List;

import lizhi.bwie.com.jingdongcom.R;
import lizhi.bwie.com.jingdongcom.model.bean.ProvinceBean;
import lizhi.bwie.com.jingdongcom.presenter.GetProvincePresenter;
import lizhi.bwie.com.jingdongcom.view.adapter.ProvinceAdapter;
import lizhi.bwie.com.jingdongcom.view.inter.GetProvinceInter;

/**
 * author:Created by Basic on 2018/3/28.
 */

public class FragmentProvince extends Fragment implements GetProvinceInter {

    private ListView list_view_addr;
    private GetProvincePresenter getProvincePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addr_choose_layout,container,false);
        list_view_addr = view.findViewById(R.id.list_view_addr);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //查询数据库中 省的数据 然后进行展示
        getProvincePresenter = new GetProvincePresenter(this);

        getProvincePresenter.getProvince(getActivity());
}

    @Override
    public void onGetProvince(final List<ProvinceBean> list) {
        //设置适配器
        ProvinceAdapter provinceAdapter = new ProvinceAdapter(getActivity(), list);
        list_view_addr.setAdapter(provinceAdapter);


        list_view_addr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //点击省的条目 需要给市那个fragment传值过去 接收到这个值之后 查询数据库 进行显示当前省下面所有的市

                //只要添加到了回退栈 fragment之间进行跳转就有了返回的效果...addToBackStack(null)
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,FragmentCity.getInstance(list.get(i))).addToBackStack(null).commit();
            }
        });
    }
}