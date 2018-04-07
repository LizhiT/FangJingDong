package lizhi.bwie.com.jingdongcom.presenter.inter;

import lizhi.bwie.com.jingdongcom.model.bean.CartBean;

/**
 * author:Created by Basic on 2018/3/29.
 */

public interface CartPresenterInter {
    void getCartDataNull();

    void getCartDataSuccess(CartBean cartBean);
}
