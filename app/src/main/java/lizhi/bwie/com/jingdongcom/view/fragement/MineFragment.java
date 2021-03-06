package lizhi.bwie.com.jingdongcom.view.fragement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import lizhi.bwie.com.jingdongcom.R;
import lizhi.bwie.com.jingdongcom.model.bean.FenleiBean;
import lizhi.bwie.com.jingdongcom.model.bean.HomeBean;
import lizhi.bwie.com.jingdongcom.presenter.PresenterHome;
import lizhi.bwie.com.jingdongcom.util.ApiUtil;
import lizhi.bwie.com.jingdongcom.util.ChenJinUtil;
import lizhi.bwie.com.jingdongcom.util.CommonUtils;
import lizhi.bwie.com.jingdongcom.util.GlideImgManager;
import lizhi.bwie.com.jingdongcom.view.activity.DetailActivity;
import lizhi.bwie.com.jingdongcom.view.activity.LoginActivity;
import lizhi.bwie.com.jingdongcom.view.activity.OrderListActivity;
import lizhi.bwie.com.jingdongcom.view.activity.UserSettingActivity;
import lizhi.bwie.com.jingdongcom.view.adapter.TuiJianAdapter;
import lizhi.bwie.com.jingdongcom.view.inter.IHomeV;
import lizhi.bwie.com.jingdongcom.view.inter.OnItemListner;


public class MineFragment extends Fragment implements IHomeV, View.OnClickListener {
    private RecyclerView tui_jian_recycler;
    private PresenterHome fragmentHomeP;
    private LinearLayout my_linear_login;
    private ImageView my_user_icon;
    private TextView my_user_name;
    private LinearLayout my_order_dfk;
    private LinearLayout my_order_dpj;
    private LinearLayout my_order_dsh;
    private LinearLayout my_order_th;
    private LinearLayout my_order_all;
    private ScrollView fragment_my_scroll;
    private RelativeLayout login_back_pic;
    private SmartRefreshLayout smart_refresh;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null);

        initView();
        initData();
        //设置点击事件
        my_linear_login.setOnClickListener(this);
        my_order_dfk.setOnClickListener(this);
        my_order_dpj.setOnClickListener(this);
        my_order_dsh.setOnClickListener(this);
        my_order_th.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initChenJin();

        tui_jian_recycler.setFocusable(false);

        //2.为你推荐,,,只需要获取一次
        fragmentHomeP = new PresenterHome(this);
        //调用p层里面的方法....https://www.zhaoapi.cn/ad/getAd
        fragmentHomeP.getNetData(ApiUtil.HOME_URL);


        smart_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //延时多久结束刷新
                smart_refresh.finishRefresh(3000);
            }
        });
        smart_refresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                smart_refresh.finishLoadmore(3000);
            }
        });
    }

    private void initChenJin() {
        ChenJinUtil.setStatusBarColor(getActivity(), Color.TRANSPARENT);
    }

    private void initView() {
        tui_jian_recycler = view.findViewById(R.id.tui_jian_recycler);
        my_linear_login = view.findViewById(R.id.my_linear_login);
        my_user_icon = view.findViewById(R.id.my_user_icon);
        my_user_name = view.findViewById(R.id.my_user_name);
        my_order_dfk = view.findViewById(R.id.my_order_dfk);
        my_order_dpj = view.findViewById(R.id.my_order_dpj);
        my_order_dsh = view.findViewById(R.id.my_order_dsh);
        my_order_th = view.findViewById(R.id.my_order_th);
        my_order_all = view.findViewById(R.id.my_order_all);
        fragment_my_scroll = view.findViewById(R.id.fragment_my_scroll);
        login_back_pic = view.findViewById(R.id.login_back_pic);
        smart_refresh = view.findViewById(R.id.smart_refresh);
    }

    /**
     * 只要对用户可见,,,就要调用
     */
    private void initData() {

        //判断一下是否登录..,..当登录成功之后,先存一下boolean值,,,在这里取出来判断
        boolean isLogin = CommonUtils.getBoolean("isLogin");

        if (isLogin) {
            if ("".equals(CommonUtils.getString("iconUrl")) || "null".equals(CommonUtils.getString("iconUrl"))) {
                //显示默认头像
                my_user_icon.setImageResource(R.drawable.user);
            } else {

                //1.加载一下头像显示...判断一下是否有头像的路径,,,没有则显示默认的头像
                //Glide.with(getActivity()).load(CommonUtils.getString("iconUrl")).into(my_user_icon);

                //圆形
                GlideImgManager.glideLoader(getActivity(), CommonUtils.getString("iconUrl"), R.drawable.user, R.drawable.user, my_user_icon, 0);
            }
            //2.先试一下用户名
            my_user_name.setText(CommonUtils.getString("name"));

            //背景图片的切换
            login_back_pic.setBackgroundResource(R.drawable.reg_bg);
        } else {
            //显示默认头像
            my_user_icon.setImageResource(R.drawable.user);
            //用户名显示 登录/注册 >
            my_user_name.setText("登录/注册 >");

            //背景图片的切换
            login_back_pic.setBackgroundResource(R.drawable.normal_regbg);

        }

    }

    @Override
    public void onResume() {

        super.onResume();

        //scrollView滚动到最上面
        //fragment_my_scroll.smoothScrollTo(0,0);

        //1.是否登录的一些操作
        initData();

    }

    /**
     * 隐藏改变的回调...是否隐藏  true表示隐藏
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(final boolean hidden) {
        super.onHiddenChanged(hidden);
        //Toast.makeText(getActivity(),hidden+"---",Toast.LENGTH_SHORT).show();

        if (!hidden) {//可见
            //scrollView滚动到最上面
            //fragment_my_scroll.smoothScrollTo(0,0);
            initChenJin();
            initData();
        }

    }

    @Override
    public void onSuccess(final HomeBean bean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //瀑布流
                tui_jian_recycler.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
                //为你推荐设置适配器
                TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(getActivity(), bean.getTuijian());
                tui_jian_recycler.setAdapter(tuiJianAdapter);

                //为你推荐的点击事件
                tuiJianAdapter.setOnItemListner(new OnItemListner() {
                    @Override
                    public void onItemClick(int position) {

                        //跳转的是自己的详情页面
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        //传递pid
                        intent.putExtra("pid", bean.getTuijian().getList().get(position).getPid());
                        startActivity(intent);

                    }

                    @Override
                    public void onItemLongClick(int position) {

                    }
                });
            }
        });
    }

    @Override
    public void onSuccessFenlei(FenleiBean bean) {

    }

    @Override
    public void onClick(View view) {
        boolean isLogin = CommonUtils.getBoolean("isLogin");

        Intent intent = new Intent();

        if (!isLogin) {
            //跳转登录注册页面
            intent.setClass(getActivity(), LoginActivity.class);
        } else {
            switch (view.getId()) {

                case R.id.my_linear_login://跳转账户设置页面
                    intent.setClass(getActivity(), UserSettingActivity.class);

                    break;
                case R.id.my_order_dfk://我的订单...代付款
                    //可以传值过去...显示哪一个tablayout
                    intent.setClass(getActivity(), OrderListActivity.class);
                    intent.putExtra("flag", 1);
                    break;
                case R.id.my_order_dpj://已支付

                    intent.setClass(getActivity(), OrderListActivity.class);
                    intent.putExtra("flag", 2);

                    break;
                case R.id.my_order_dsh://已取消

                    intent.setClass(getActivity(), OrderListActivity.class);
                    intent.putExtra("flag", 3);
                    break;
                case R.id.my_order_th://退化
                    intent.setClass(getActivity(), OrderListActivity.class);
                    break;
            }
        }
        //开启
        startActivity(intent);
    }
}
