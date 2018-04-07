package lizhi.bwie.com.jingdongcom.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import lizhi.bwie.com.jingdongcom.R;
import lizhi.bwie.com.jingdongcom.model.bean.RegistBean;
import lizhi.bwie.com.jingdongcom.presenter.RegistPresenter;
import lizhi.bwie.com.jingdongcom.util.ApiUtil;
import lizhi.bwie.com.jingdongcom.view.inter.RegistActivityInter;

public class RegistActivity extends AppCompatActivity implements RegistActivityInter, View.OnClickListener {

    private EditText edit_phone;
    private EditText edit_pwd;
    private RegistPresenter registPresenter;
    private ImageView cha_iamge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        edit_phone = findViewById(R.id.edit_phone);
        edit_pwd = findViewById(R.id.edit_pwd);
        cha_iamge = findViewById(R.id.cha_iamge);

        //中间者
        registPresenter = new RegistPresenter(this);

        cha_iamge.setOnClickListener(this);
    }

    @Override
    public void onRegistSuccess(RegistBean registBean) {
        String code = registBean.getCode();
        if ("1".equals(code)) {//注册失败
            Toast.makeText(RegistActivity.this,registBean.getMsg(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(RegistActivity.this,registBean.getMsg(),Toast.LENGTH_SHORT).show();
            //并且结束显示
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cha_iamge://叉

                finish();
                break;
        }
    }

    public void resign(View view) {
        String name = edit_phone.getText().toString();
        String pwd = edit_pwd.getText().toString();

        registPresenter.registUser(ApiUtil.REGIST_URL,name,pwd);
    }
}
