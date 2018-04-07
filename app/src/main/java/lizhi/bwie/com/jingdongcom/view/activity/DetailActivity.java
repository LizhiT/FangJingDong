package lizhi.bwie.com.jingdongcom.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMShareAPI;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import lizhi.bwie.com.jingdongcom.R;
import lizhi.bwie.com.jingdongcom.model.bean.AddCartBean;
import lizhi.bwie.com.jingdongcom.model.bean.DeatilBean;
import lizhi.bwie.com.jingdongcom.presenter.AddCartPresenter;
import lizhi.bwie.com.jingdongcom.presenter.DeatailPresenter;
import lizhi.bwie.com.jingdongcom.util.ApiUtil;
import lizhi.bwie.com.jingdongcom.util.CommonUtils;
import lizhi.bwie.com.jingdongcom.util.GlideImageLoader;
import lizhi.bwie.com.jingdongcom.util.ShareUtil;
import lizhi.bwie.com.jingdongcom.view.inter.ActivityAddCartInter;
import lizhi.bwie.com.jingdongcom.view.inter.DetailActivityInter;

public class DetailActivity extends AppCompatActivity implements DetailActivityInter, ActivityAddCartInter {

    private ImageView mBack;
    private ImageView mShare;
    private Banner mDetailBanner;
    private TextView mTitle;
    private TextView mTitle2;
    private TextView mDetailPrice;
    private LinearLayout mGocart;
    private TextView mAddcart;
    private int pid;
    private DeatailPresenter deatailPresenter;
    private AddCartPresenter addCartPresenter;
    private DeatilBean deatilBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initListener();
        initBanner();
        pid = getIntent().getIntExtra("pid", -1);
        if (pid != -1) {
            deatailPresenter.getDetailData(ApiUtil.DETAIL_URL, pid);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void initListener() {
        mGocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("ims", 1);
                startActivity(intent);
            }
        });
        mAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                if (CommonUtils.getBoolean("isLogin")) {
                    addCartPresenter.addToCart(ApiUtil.ADD_CART_URL, CommonUtils.getString("uid"), pid);

                } else {
                    Intent intent = new Intent(DetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (deatilBean != null) {
                    //final Activity activity 分享的activity的上下文,
                    // String WebUrl 分享的商品的链接,
                    // String title 分享的商品的标题,
                    // String description 商品的描述,
                    // String imageUrl 商品的图片...如果没有图片传"",
                    // int imageID 本地商品的图片
                    DeatilBean.DataBean data = deatilBean.getData();
                    ShareUtil.shareWeb(DetailActivity.this, data.getDetailUrl(), data.getTitle(), "我在京东发现一个好的商品,赶紧来看看吧!", data.getImages().split("\\|")[0], R.mipmap.ic_launcher);
                }
            }
        });
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.imageView);
        mShare = (ImageView) findViewById(R.id.imageView3);
        mDetailBanner = (Banner) findViewById(R.id.detail_banner);
        mTitle = (TextView) findViewById(R.id.title);
        mTitle2 = (TextView) findViewById(R.id.title2);
        mDetailPrice = (TextView) findViewById(R.id.detail_price);
        mGocart = (LinearLayout) findViewById(R.id.gocart);
        mAddcart = (TextView) findViewById(R.id.addcart);
        //创建presenter
        deatailPresenter = new DeatailPresenter(this);
        addCartPresenter = new AddCartPresenter(this);
    }

    @Override
    public void onCartAddSuccess(AddCartBean addCartBean) {
        Toast.makeText(DetailActivity.this, addCartBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(final DeatilBean deatilBean) {
        this.deatilBean = deatilBean;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //设置数据显示
                mTitle.setText(deatilBean.getData().getTitle());
                mTitle2.setText(deatilBean.getData().getSubhead());
                mDetailPrice.setText("优惠价:" + deatilBean.getData().getBargainPrice());
                String[] strings = deatilBean.getData().getImages().split("\\|");
                final ArrayList<String> imageUrls = new ArrayList<>();
                for (int i = 0; i < strings.length; i++) {
                    imageUrls.add(strings[i]);
                }
                mDetailBanner.setImages(imageUrls);
                //bannner点击事件进行跳转
                mDetailBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(DetailActivity.this, ImageScaleActivity.class);
                        //传递的数据...整个轮播图数据的集合需要传递,,,当前展示的图片的位置需要传递postion
                        //intent传递可以传的数据...基本数据类型...引用数据类型(必须序列化,所有的类,包括内部类实现serilizable接口)...bundle
                        intent.putStringArrayListExtra("imageUrls", imageUrls);
                        intent.putExtra("position", position);
                        startActivity(intent);
                    }
                });

                mDetailBanner.start();
            }
        });
    }

    private void initBanner() {

        //设置banner样式...CIRCLE_INDICATOR_TITLE包含标题
        mDetailBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mDetailBanner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        mDetailBanner.isAutoPlay(true);
        //设置轮播时间
        mDetailBanner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        mDetailBanner.setIndicatorGravity(BannerConfig.CENTER);

    }
}
