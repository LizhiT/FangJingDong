package lizhi.bwie.com.jingdongcom.presenter;

import lizhi.bwie.com.jingdongcom.model.ModelHome;
import lizhi.bwie.com.jingdongcom.model.bean.FenleiBean;
import lizhi.bwie.com.jingdongcom.model.bean.HomeBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.IHomeP;
import lizhi.bwie.com.jingdongcom.view.inter.IHomeV;

/**
 * author:Created by Basic on 2018/3/15.
 */

public class PresenterHome implements IHomeP {

    private final ModelHome modelHome;
    private final IHomeV Ihomev;
    public PresenterHome(IHomeV Ihomev) {
       this.Ihomev = Ihomev;
        modelHome = new ModelHome(this);
    }

    public void getNetData(String homeUrl) {
        modelHome.getData(homeUrl);
    }

    @Override
    public void onSuccess(HomeBean bean) {
        Ihomev.onSuccess(bean);
    }

    @Override
    public void onSuccessFenlei(FenleiBean fenLeiBean) {
        Ihomev.onSuccessFenlei(fenLeiBean);
    }

    public void getFenLeiData(String fenLeiUrl) {
        modelHome.getFenleiData(fenLeiUrl);
    }
}
