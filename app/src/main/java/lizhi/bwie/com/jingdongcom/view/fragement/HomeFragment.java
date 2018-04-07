package lizhi.bwie.com.jingdongcom.view.fragement;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunfusheng.marqueeview.MarqueeView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import lizhi.bwie.com.jingdongcom.R;
import lizhi.bwie.com.jingdongcom.model.bean.FenleiBean;
import lizhi.bwie.com.jingdongcom.model.bean.HomeBean;
import lizhi.bwie.com.jingdongcom.presenter.PresenterHome;
import lizhi.bwie.com.jingdongcom.util.ApiUtil;
import lizhi.bwie.com.jingdongcom.util.ChenJinUtil;
import lizhi.bwie.com.jingdongcom.util.GlideImageLoader;
import lizhi.bwie.com.jingdongcom.util.ObservableScrollView;
import lizhi.bwie.com.jingdongcom.view.activity.DetailActivity;
import lizhi.bwie.com.jingdongcom.view.activity.SearchActivity;
import lizhi.bwie.com.jingdongcom.view.activity.WebActivity;
import lizhi.bwie.com.jingdongcom.view.adapter.HengXiangAdapter;
import lizhi.bwie.com.jingdongcom.view.adapter.HomeMiaoshaAdapter;
import lizhi.bwie.com.jingdongcom.view.adapter.TuiJianAdapter;
import lizhi.bwie.com.jingdongcom.view.inter.OnItemListner;
import lizhi.bwie.com.jingdongcom.view.inter.IHomeV;


public class HomeFragment extends Fragment implements View.OnClickListener, IHomeV {
    private final String TAG_MARGIN_ADDED = "marginAdded";
    private View view;
    //存放banner图片的集合
    private List<String> banner_images = new ArrayList<>();
    //存放bannertitle的集合
    private List<String> banner_titles = new ArrayList<>();
    private Banner mBanner;
    private RecyclerView mHomeHengxiang;
    private MarqueeView mHomePaomadeng;
    private RecyclerView mHomeMiaosha;
    private PresenterHome presenterHome;
    private HomeMiaoshaAdapter adapter;
    private RecyclerView mHomeTuijian;
    private SmartRefreshLayout mSmartrefresh;
    private LinearLayout linear_include;
    private ObservableScrollView observe_scroll_view;
    private LinearLayout mSaoyisao;

    int REQUEST_CODE = 1;
    /**
     * 年货超级秒杀,好货低至1折
     */
    private TextView mUnclickText;
    private LinearLayout mSearchBackground;
    private RelativeLayout mSearch;
    private ImageView mChangeA4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        //ZXingLibrary初始化
        ZXingLibrary.initDisplayOpinion(getActivity());
        //  初始化控件
        initView(view);
        //初始化跑马灯
        initMarqueeView();
        //初始化刷新刷新
        initRefresh();
        //开始沉浸
        ChenJinUtil.startChenJin(getActivity());
        //标题栏
        initTitleBar();
        //获取摄像头权限
        getCameraPermission();
        mSaoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        //跳转到搜索界面
        initSearch();
        return view;
    }

    private void initSearch() {
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getCameraPermission() {
        if (Build.VERSION.SDK_INT > 22) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA}, 2);
            } else {
                //说明已经获取到摄像头权限了 想干嘛干嘛
            }
        } else {
            //这个说明系统版本在6.0之下，不需要动态获取权限。
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //设置titlebar  还没有设置完成 待解决(文字，文字背景等变色)
    private void initTitleBar() {
        //linearLayout在view绘制的时候外面包裹一层relativeLayout
        //应该尽量减少使用linearLayout...view优化
        addMargin();
        ViewTreeObserver viewTreeObserver = linear_include.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linear_include.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                final int height = linear_include.getHeight();

                observe_scroll_view.setScrollViewListener(new ObservableScrollView.IScrollViewListener() {
                    @Override
                    public void onScrollChanged(int x, int y, int oldx, int oldy) {
                        if (y <= 0) {
                            addMargin();
                            ChenJinUtil.setStatusBarColor(getActivity(), Color.TRANSPARENT);
                            linear_include.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得
                        } else if (y > 0 && y < height) {

                            if (y > ChenJinUtil.getStatusBarHeight(getActivity())) {
                                //去掉margin
                                removeMargin();
                                //状态栏为灰色
                                ChenJinUtil.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimaryDark));
                            }

                            float scale = (float) y / height;
                            float alpha = (255 * scale);
                            // 只是layout背景透明(仿知乎滑动效果)
                            linear_include.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
//                            mUnclickText.setBackgroundColor(Color.argb((int) alpha, 180, 180, 180));
//                            mUnclickText.setTextColor(Color.argb((int) alpha, 255, 255, 255));
//                            mChangeA4.setBackgroundResource(R.drawable.a_5);


                        } else {
                            linear_include.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
//                            mUnclickText.setTextColor(Color.argb((int) 230, 230, 230, 230));
//                            mUnclickText.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
//                            mChangeA4.setBackgroundResource(R.drawable.a_4);

                        }
                    }
                });
            }
        });
    }

    private void addMargin() {
        //给标题调价margin
        if (!TAG_MARGIN_ADDED.equals(linear_include.getTag())) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) linear_include.getLayoutParams();
            // 添加的间隔大小就是状态栏的高度
            params.topMargin += ChenJinUtil.getStatusBarHeight(getActivity());
            linear_include.setLayoutParams(params);
            linear_include.setTag(TAG_MARGIN_ADDED);
        }
    }

    private void removeMargin() {
        if (TAG_MARGIN_ADDED.equals(linear_include.getTag())) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) linear_include.getLayoutParams();
            // 移除的间隔大小就是状态栏的高度
            params.topMargin -= ChenJinUtil.getStatusBarHeight(getActivity());
            linear_include.setLayoutParams(params);
            linear_include.setTag(null);
        }
    }

    private void initRefresh() {
        mSmartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
    }

    private void initView(View view) {
        presenterHome = new PresenterHome(this);
        presenterHome.getNetData(ApiUtil.HOME_URL);
        presenterHome.getFenLeiData(ApiUtil.FEN_LEI_URL);
        mBanner = (Banner) view.findViewById(R.id.banner);
        mHomeHengxiang = (RecyclerView) view.findViewById(R.id.home_hengxiang);
        mHomePaomadeng = (MarqueeView) view.findViewById(R.id.home_paomadeng);
        mHomeMiaosha = (RecyclerView) view.findViewById(R.id.home_miaosha);
        mHomeTuijian = (RecyclerView) view.findViewById(R.id.home_tuijian);
        mSmartrefresh = (SmartRefreshLayout) view.findViewById(R.id.smartrefresh);
        linear_include = (LinearLayout) view.findViewById(R.id.linear_include);
        observe_scroll_view = (ObservableScrollView) view.findViewById(R.id.observe_scroll_view);
        mSaoyisao = (LinearLayout) view.findViewById(R.id.saoyisao);
        mSaoyisao.setOnClickListener(this);
        mUnclickText = (TextView) view.findViewById(R.id.unclick_text);
        mSearchBackground = (LinearLayout) view.findViewById(R.id.search_background);
        mSearch = (RelativeLayout) view.findViewById(R.id.search);
        mChangeA4 = (ImageView) view.findViewById(R.id.change_a4);
    }

    @Override
    public void onSuccess(final HomeBean bean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), bean.getCode(), Toast.LENGTH_SHORT).show();
                //关于banner的设置
                for (int i = 0; i < bean.getData().size(); i++) {
                    String s = bean.getData().get(i).getIcon();
                    //把图片和文字分别添加到集合
                    banner_images.add(s);
                    banner_titles.add(bean.getData().get(i).getTitle());
                    initBanner();
                    mBanner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            HomeBean.DataBean dataBean = bean.getData().get(position);
                            if (dataBean.getType() == 0) {
                                Intent intent = new Intent(getActivity(), WebActivity.class);
                                intent.putExtra("urls", dataBean.getUrl());
                                startActivity(intent);
                            } else {
                                Toast.makeText(getActivity(), "您点击的该页面正在添加中", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    //设置开始
                    mBanner.start();
                }
                //------------秒杀界面
                mHomeMiaosha.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.HORIZONTAL, false));
                adapter = new HomeMiaoshaAdapter(getActivity(), bean.getMiaosha());
                mHomeMiaosha.setAdapter(adapter);
                //条目的点击事件
                adapter.setOnItemListner(new OnItemListner() {
                    @Override
                    public void onItemClick(int position) {
                        //跳转到下一个页面....传值过去...webView页面
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        String urls = bean.getMiaosha().getList().get(position).getDetailUrl();
                        intent.putExtra("urls", urls);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(int position) {
                    }
                });
                //------------为你推荐界面
                mHomeTuijian.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
                //为你推荐设置适配器
                TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(getActivity(), bean.getTuijian());
                mHomeTuijian.setAdapter(tuiJianAdapter);
                //设置滑动冲突
                mHomeTuijian.setNestedScrollingEnabled(false);
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

    //         分类的数据
    @Override
    public void onSuccessFenlei(final FenleiBean bean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //如果要展示数据的话必须先设置布局管理器....上下文,,,表示几行或者几列,,,表示方向,水平或者竖直,,,是否反转布局展示
                mHomeHengxiang.setLayoutManager(new GridLayoutManager(getActivity(), 2, OrientationHelper.HORIZONTAL, false));

                HengXiangAdapter hengXiangAdapter = new HengXiangAdapter(getActivity(), bean);
                //设置适配器
                mHomeHengxiang.setAdapter(hengXiangAdapter);

                //设置条目的点击事件...实际是适配器的点击事件
                hengXiangAdapter.setOnItemListner(new OnItemListner() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(getActivity(), "点击事件执行", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(int position) {
                        Toast.makeText(getActivity(), "长按事件执行", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    //banner初始化
    private void initBanner() {
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置banner标题
        mBanner.setBannerTitles(banner_titles);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.ScaleInOut);
        //设置图片
        mBanner.setImages(banner_images);
        //设置间隔时间
        mBanner.setDelayTime(3000);
        //设置是否自动播放
        mBanner.isAutoPlay(true);
        //设置banner样式...CIRCLE_INDICATOR_TITLE包含标题
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
    }

    //textview通过setEillpsize显式设置文字多了以后显示省略号
    private void initMarqueeView() {
        List<String> info = new ArrayList<>();
        info.add("热门：欢迎访问京东");
        info.add("Hot 给自己换手机很难吗？让一加5T改变你的生活！");
        info.add("最新 清丽的人声，铁三角蓝牙耳机颠覆你的声音的认识。");
        mHomePaomadeng.startWithList(info);
    }

    @Override
    public void onClick(View view) {
    }
}
