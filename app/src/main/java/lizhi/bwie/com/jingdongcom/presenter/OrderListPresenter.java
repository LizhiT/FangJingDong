package lizhi.bwie.com.jingdongcom.presenter;

import lizhi.bwie.com.jingdongcom.model.OrderListModel;
import lizhi.bwie.com.jingdongcom.model.bean.OrderListBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.OrderListPresenterInter;
import lizhi.bwie.com.jingdongcom.view.inter.FragmentOrderListInter;

/**
 * author:Created by Basic on 2018/3/29.
 */

public class OrderListPresenter implements OrderListPresenterInter {

    private FragmentOrderListInter fragmentOrderListInter;
    private OrderListModel orderListModel;

    public OrderListPresenter(FragmentOrderListInter fragmentOrderListInter) {
        this.fragmentOrderListInter = fragmentOrderListInter;
        orderListModel = new OrderListModel(this);
    }

    public void getOrderData(String orderListUrl, String uid, int page) {

        orderListModel.getOrderData(orderListUrl,uid,page);

    }

    @Override
    public void onOrderDataSuccess(OrderListBean orderListBean) {

        fragmentOrderListInter.onOrderDataSuccess(orderListBean);
    }
}
