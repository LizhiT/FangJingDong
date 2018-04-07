package lizhi.bwie.com.jingdongcom.presenter;

import lizhi.bwie.com.jingdongcom.model.GetAllAddrModel;
import lizhi.bwie.com.jingdongcom.model.bean.GetAllAddrBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.GetAllAddrPresenterInter;
import lizhi.bwie.com.jingdongcom.view.inter.GetAllAddrInter;

/**
 * author:Created by Basic on 2018/3/28.
 */

public class GetAllAddrPresenter implements GetAllAddrPresenterInter {

    private GetAllAddrInter getAllAddrInter;
    private GetAllAddrModel getAllAddrModel;

    public GetAllAddrPresenter(GetAllAddrInter getAllAddrInter) {
        this.getAllAddrInter = getAllAddrInter;
        getAllAddrModel = new GetAllAddrModel(this);
    }

    public void getAllAddr(String getAllAddrUrl, String uid) {
        getAllAddrModel.getAllAddr(getAllAddrUrl, uid);
    }

    @Override
    public void onGetAllAddrSuccess(GetAllAddrBean getAllAddrBean) {
        getAllAddrInter.onGetAllAddrSuccess(getAllAddrBean);
    }
}
