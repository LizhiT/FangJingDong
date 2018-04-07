package lizhi.bwie.com.jingdongcom.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import lizhi.bwie.com.jingdongcom.R;
import lizhi.bwie.com.jingdongcom.util.ChenJinUtil;
import lizhi.bwie.com.jingdongcom.view.fragement.CLassifyFragment;
import lizhi.bwie.com.jingdongcom.view.fragement.DiscoverFragment;
import lizhi.bwie.com.jingdongcom.view.fragement.HomeFragment;
import lizhi.bwie.com.jingdongcom.view.fragement.MineFragment;
import lizhi.bwie.com.jingdongcom.view.fragement.ShoopingcartFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup group;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private CLassifyFragment classifyFragment;
    private DiscoverFragment discoverFragment;
    private MineFragment mineFragment;
    private ShoopingcartFragment shoopingcartFragment;
    private View mV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        homeFragment = new HomeFragment();
        manager = getSupportFragmentManager();
        group.setOnCheckedChangeListener(this);
        //        设置view透明度
        mV.getBackground().setAlpha(20);
        ChenJinUtil.startChenJin(MainActivity.this);
        int ims = getIntent().getIntExtra("ims", 0);
        if (ims == 1) {
            manager.beginTransaction().replace(R.id.frame, new ShoopingcartFragment()).commit();
            group.setVisibility(View.GONE);
        } else {
            manager.beginTransaction().add(R.id.frame, homeFragment).commit();
        }
    }

    private void initView() {
        group = (RadioGroup) findViewById(R.id.group);
        mV = (View) findViewById(R.id.v);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        transaction = manager.beginTransaction();

        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (shoopingcartFragment != null) {
            transaction.hide(shoopingcartFragment);
        }
        if (classifyFragment != null) {
            transaction.hide(classifyFragment);
        }
        if (discoverFragment != null) {
            transaction.hide(discoverFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
        switch (i) {
            case R.id.select_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.frame, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case R.id.select_classify:
                if (classifyFragment == null) {
                    classifyFragment = new CLassifyFragment();
                    transaction.add(R.id.frame, classifyFragment);
                } else {
                    transaction.show(classifyFragment);
                }
                break;
            case R.id.select_discover:
                if (discoverFragment == null) {
                    discoverFragment = new DiscoverFragment();
                    transaction.add(R.id.frame, discoverFragment);
                } else {
                    transaction.show(discoverFragment);
                }
                break;
            case R.id.select_buycart:
                if (shoopingcartFragment == null) {
                    shoopingcartFragment = new ShoopingcartFragment();
                    transaction.add(R.id.frame, shoopingcartFragment);
                } else {
                    transaction.show(shoopingcartFragment);
                }
                break;
            case R.id.select_mine:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.frame, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commit();
    }
}
