package lizhi.bwie.com.jingdongcom.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import lizhi.bwie.com.jingdongcom.R;
import lizhi.bwie.com.jingdongcom.util.ChenJinUtil;

public class FlashActivity extends AppCompatActivity {

    //text的值是3
    private TextView goto_next;

    //在向服务端发送获取验证码成功的回调函数中，开始发消息：

    //消息的处理：
    private int time = 4;

    private Handler mHandler = new Handler();
    private Runnable myRunnale = new Runnable() {
        @Override
        public void run() {
            time--;
            if(time>0){
                mHandler.postDelayed(myRunnale,1000);
                goto_next.setText("跳转"+time);
                goto_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goMain();
                    }
                });
            }else{
                goMain();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        initView();
        mHandler.post(myRunnale);
        ChenJinUtil.setStatusBarColor(this, Color.TRANSPARENT);
        ChenJinUtil.startChenJin(FlashActivity.this);

    }

    private void goMain() {
        Intent intent = new Intent(FlashActivity.this, MainActivity.class);
        startActivity(intent);
    }


    private void initView() {
        goto_next = (TextView) findViewById(R.id.goto_next);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
           wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
