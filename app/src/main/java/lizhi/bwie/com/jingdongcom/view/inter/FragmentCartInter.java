package lizhi.bwie.com.jingdongcom.view.inter;

import lizhi.bwie.com.jingdongcom.model.bean.CartBean;

/**
 * author:Created by Basic on 2018/3/29.
 */

public interface FragmentCartInter {
    void getCartDataNull();

    void getCartDataSuccess(CartBean cartBean);
}
