package lizhi.bwie.com.jingdongcom.presenter;

import lizhi.bwie.com.jingdongcom.model.ProductListModel;
import lizhi.bwie.com.jingdongcom.model.bean.ProductListBean;
import lizhi.bwie.com.jingdongcom.view.inter.ProductListActivityInter;
import lizhi.bwie.com.jingdongcom.view.inter.ProductListPresenterInter;

/**
 * author:Created by Basic on 2018/3/27.
 */

public class ProductListPresenter implements ProductListPresenterInter {

    private ProductListModel productListModel;
    private ProductListActivityInter productListActivityInter;

    public ProductListPresenter(ProductListActivityInter productListActivityInter) {
        this.productListActivityInter = productListActivityInter;

        productListModel = new ProductListModel(this);
    }

    public void getProductData(String seartchUrl, String keywords, int page) {

        productListModel.getProductData(seartchUrl,keywords,page);
    }

    @Override
    public void onSuccess(ProductListBean productListBean) {
        productListActivityInter.getProductDataSuccess(productListBean);
    }
}
