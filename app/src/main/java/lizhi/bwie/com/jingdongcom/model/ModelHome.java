package lizhi.bwie.com.jingdongcom.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lizhi.bwie.com.jingdongcom.model.bean.FenleiBean;
import lizhi.bwie.com.jingdongcom.model.bean.HomeBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.IHomeP;
import lizhi.bwie.com.jingdongcom.util.OkHttp3Util_03;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by Basic on 2018/3/15.
 */

public class ModelHome {
    private IHomeP iHomeP;
    public ModelHome(IHomeP iHomeP) {
        this.iHomeP = iHomeP;
    }

    public void getData(String homeUrl) {
        Map<String, String> params = new HashMap<>();
        OkHttp3Util_03.doPost(homeUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String json = response.body().string();
                    HomeBean bean = new Gson().fromJson(json, HomeBean.class);
                    iHomeP.onSuccess(bean);
                }
            }
        });
    }

    public void getFenleiData(String fenLeiUrl) {
        Map<String, String> params = new HashMap<>();


        OkHttp3Util_03.doPost(fenLeiUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    //解析
                    final FenleiBean fenLeiBean = new Gson().fromJson(json,FenleiBean.class);
                    //回调给主线程
                    iHomeP.onSuccessFenlei(fenLeiBean);

                }
            }
        });

    }
}
