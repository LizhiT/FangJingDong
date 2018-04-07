package lizhi.bwie.com.jingdongcom.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import lizhi.bwie.com.jingdongcom.R;

public class WebActivity extends AppCompatActivity {

    private WebView mWbWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        Intent intent = getIntent();
        String urls = intent.getStringExtra("urls");
        mWbWeb.loadUrl(urls);
    }

    private void initView() {
        mWbWeb = (WebView) findViewById(R.id.wb_web);
    }
}
