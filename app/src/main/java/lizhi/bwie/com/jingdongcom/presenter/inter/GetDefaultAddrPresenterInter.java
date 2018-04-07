package lizhi.bwie.com.jingdongcom.presenter.inter;

import lizhi.bwie.com.jingdongcom.model.bean.DefaultAddrBean;

/**
 * author:Created by Basic on 2018/3/29.
 */

public interface GetDefaultAddrPresenterInter {
    void onGetDefaultAddrSuccess(DefaultAddrBean defaultAddrBean);

    void onGetDefaultAddrEmpty();
}
