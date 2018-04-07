package lizhi.bwie.com.jingdongcom.view.inter;

import lizhi.bwie.com.jingdongcom.model.bean.LoginBean;

/**
 * author:Created by Basic on 2018/3/28.
 */

public interface LoginPresenterInter {

    void onSuccess(LoginBean loginBean);


    void onSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl);
}
