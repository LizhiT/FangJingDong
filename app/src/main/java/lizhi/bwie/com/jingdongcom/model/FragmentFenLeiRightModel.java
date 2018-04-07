package lizhi.bwie.com.jingdongcom.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lizhi.bwie.com.jingdongcom.model.bean.ChildFenLeiBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.FenLeiRightPresenterInter;
import lizhi.bwie.com.jingdongcom.util.OkHttp3Util_03;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by Basic on 2018/3/27.
 */

public class FragmentFenLeiRightModel {

    private FenLeiRightPresenterInter fenLeiRightPresenterInter;

    public FragmentFenLeiRightModel(FenLeiRightPresenterInter fenLeiRightPresenterInter) {
        this.fenLeiRightPresenterInter = fenLeiRightPresenterInter;
    }

    public void getChildData(String childFenLeiUrl, int cid) {

        Map<String, String> params = new HashMap<>();
        params.put("cid", String.valueOf(cid));

        OkHttp3Util_03.doPost(childFenLeiUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();

                    //解析
                    final ChildFenLeiBean childFenLeiBean = new Gson().fromJson(json, ChildFenLeiBean.class);


                    fenLeiRightPresenterInter.onSuncess(childFenLeiBean);


                }
            }
        });

    }
}
