package lizhi.bwie.com.jingdongcom.presenter;

import lizhi.bwie.com.jingdongcom.model.AddCartModel;
import lizhi.bwie.com.jingdongcom.model.bean.AddCartBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.AddCartPresenterInter;
import lizhi.bwie.com.jingdongcom.view.inter.ActivityAddCartInter;

/**
 * author:Created by Basic on 2018/3/27.
 */

public class AddCartPresenter implements AddCartPresenterInter {

    private ActivityAddCartInter activityAddCartInter;
    private AddCartModel addCartModel;

    public AddCartPresenter(ActivityAddCartInter activityAddCartInter) {
        this.activityAddCartInter = activityAddCartInter;

        addCartModel = new AddCartModel(this);
    }

    public void addToCart(String addCart, String uid, int pid) {

        addCartModel.addToCart(addCart,uid,pid);

    }

    @Override
    public void onCartAddSuccess(AddCartBean addCartBean) {
        activityAddCartInter.onCartAddSuccess(addCartBean);
    }
}
