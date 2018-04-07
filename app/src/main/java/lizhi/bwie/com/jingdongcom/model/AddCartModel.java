package lizhi.bwie.com.jingdongcom.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lizhi.bwie.com.jingdongcom.model.bean.AddCartBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.AddCartPresenterInter;
import lizhi.bwie.com.jingdongcom.util.OkHttp3Util_03;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by Basic on 2018/3/27.
 */

public class AddCartModel {


    private AddCartPresenterInter addCartPresenterInter;

    public AddCartModel(AddCartPresenterInter addCartPresenterInter) {
        this.addCartPresenterInter = addCartPresenterInter;
    }

    public void addToCart(String addCart, String uid, int pid) {

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("pid", String.valueOf(pid));

        OkHttp3Util_03.doPost(addCart, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();

                    final AddCartBean addCartBean = new Gson().fromJson(json, AddCartBean.class);

                    addCartPresenterInter.onCartAddSuccess(addCartBean);

                }
            }
        });

    }
}