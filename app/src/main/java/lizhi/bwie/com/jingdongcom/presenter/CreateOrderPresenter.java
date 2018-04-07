package lizhi.bwie.com.jingdongcom.presenter;

import lizhi.bwie.com.jingdongcom.model.CreateOrderModel;
import lizhi.bwie.com.jingdongcom.model.bean.CreateOrderBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.CreateOrderPresenterInter;
import lizhi.bwie.com.jingdongcom.view.inter.CreateOrderInter;

/**
 * author:Created by Basic on 2018/3/29.
 */

public class CreateOrderPresenter implements CreateOrderPresenterInter {

    private CreateOrderInter createOrderInter;
    private CreateOrderModel createOrderModel;

    public CreateOrderPresenter(CreateOrderInter createOrderInter) {
        this.createOrderInter = createOrderInter;
        createOrderModel = new CreateOrderModel(this);
    }

    public void createOrder(String createOrderUrl, String uid, double price) {

        createOrderModel.createOrder(createOrderUrl,uid,price);

    }

    @Override
    public void onOrderCreateSuccess(CreateOrderBean createOrderBean) {
        createOrderInter.onCreateOrderSuccess(createOrderBean);
    }
}
