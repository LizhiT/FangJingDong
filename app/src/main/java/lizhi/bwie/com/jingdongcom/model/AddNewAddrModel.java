package lizhi.bwie.com.jingdongcom.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lizhi.bwie.com.jingdongcom.model.bean.AddNewAddrBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.AddNewAddrPresenterInter;
import lizhi.bwie.com.jingdongcom.util.CommonUtils;
import lizhi.bwie.com.jingdongcom.util.OkHttp3Util_03;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by Basic on 2018/3/28.
 */

public class AddNewAddrModel {


    private AddNewAddrPresenterInter addNewAddrPresenterInter;

    public AddNewAddrModel(AddNewAddrPresenterInter addNewAddrPresenterInter) {
        this.addNewAddrPresenterInter = addNewAddrPresenterInter;
    }

    public void addNewAddr(String addNewAddrUrl, String uid, String addr, String phone, String name) {

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("addr",addr);
        params.put("mobile",phone);
        params.put("name",name);


        OkHttp3Util_03.doPost(addNewAddrUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    String json = response.body().string();
                    final AddNewAddrBean addNewAddrBean = new Gson().fromJson(json,AddNewAddrBean.class);

                    CommonUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            addNewAddrPresenterInter.onAddAddrSuccess(addNewAddrBean);
                        }
                    });

                }
            }
        });
    }
}
