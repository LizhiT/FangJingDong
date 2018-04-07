package lizhi.bwie.com.jingdongcom.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lizhi.bwie.com.jingdongcom.model.bean.RegistBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.RegistPresenterInter;
import lizhi.bwie.com.jingdongcom.util.CommonUtils;
import lizhi.bwie.com.jingdongcom.util.OkHttp3Util_03;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by Basic on 2018/3/28.
 */

public class RegistModel {
    private RegistPresenterInter registPresenterInter;

    public RegistModel(RegistPresenterInter registPresenterInter) {
        this.registPresenterInter = registPresenterInter;
    }

    public void registUser(String registUrl, String name, String pwd) {

        Map<String, String> params = new HashMap<>();
        params.put("mobile",name);
        params.put("password",pwd);

        OkHttp3Util_03.doPost(registUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    String json = response.body().string();

                    final RegistBean registBean = new Gson().fromJson(json,RegistBean.class);

                    CommonUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            registPresenterInter.onRegistSuccess(registBean);
                        }
                    });

                }
            }
        });

    }
}