package lizhi.bwie.com.jingdongcom.presenter;

import lizhi.bwie.com.jingdongcom.model.RegistModel;
import lizhi.bwie.com.jingdongcom.model.bean.RegistBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.RegistPresenterInter;
import lizhi.bwie.com.jingdongcom.view.inter.RegistActivityInter;

/**
 * author:Created by Basic on 2018/3/28.
 */

public class RegistPresenter implements RegistPresenterInter {

    private RegistActivityInter registActivityInter;
    private RegistModel registModel;

    public RegistPresenter(RegistActivityInter registActivityInter) {
        this.registActivityInter = registActivityInter;
        registModel = new RegistModel(this);
    }

    public void registUser(String registUrl, String name, String pwd) {

        registModel.registUser(registUrl,name,pwd);
    }

    @Override
    public void onRegistSuccess(RegistBean registBean) {
        registActivityInter.onRegistSuccess(registBean);
    }
}
