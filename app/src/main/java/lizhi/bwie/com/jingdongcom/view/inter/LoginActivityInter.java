package lizhi.bwie.com.jingdongcom.view.inter;

import lizhi.bwie.com.jingdongcom.model.bean.LoginBean;

/**
 * author:Created by Basic on 2018/3/28.
 */

public interface LoginActivityInter {

    void getLoginSuccess(LoginBean loginBean);


    void getLoginSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl);
}
