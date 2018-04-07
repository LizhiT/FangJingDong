package lizhi.bwie.com.jingdongcom.presenter.inter;

import lizhi.bwie.com.jingdongcom.model.bean.FenleiBean;
import lizhi.bwie.com.jingdongcom.model.bean.HomeBean;

/**
 * author:Created by Basic on 2018/3/15.
 */

public interface IHomeP {
    void onSuccess(HomeBean bean);

    void onSuccessFenlei(FenleiBean fenLeiBean);
}
