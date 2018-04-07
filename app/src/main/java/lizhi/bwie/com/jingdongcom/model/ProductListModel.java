package lizhi.bwie.com.jingdongcom.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lizhi.bwie.com.jingdongcom.model.bean.ProductListBean;
import lizhi.bwie.com.jingdongcom.util.OkHttp3Util_03;
import lizhi.bwie.com.jingdongcom.view.inter.ProductListPresenterInter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by Basic on 2018/3/27.
 */

public class ProductListModel {
    private ProductListPresenterInter productListPresenterInter;

    public ProductListModel(ProductListPresenterInter productListPresenterInter) {
        this.productListPresenterInter = productListPresenterInter;
    }

    public void getProductData(String seartchUrl, String keywords, int page) {

        Map<String, String> params = new HashMap<>();
        params.put("keywords", keywords);
        params.put("page", String.valueOf(page));

        OkHttp3Util_03.doPost(seartchUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    //解析
                    final ProductListBean productListBean = new Gson().fromJson(json, ProductListBean.class);
                    productListPresenterInter.onSuccess(productListBean);

                }
            }
        });

    }
}
