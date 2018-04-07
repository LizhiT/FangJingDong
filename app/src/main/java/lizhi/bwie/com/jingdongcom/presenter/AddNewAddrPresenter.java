package lizhi.bwie.com.jingdongcom.presenter;

import lizhi.bwie.com.jingdongcom.model.AddNewAddrModel;
import lizhi.bwie.com.jingdongcom.model.bean.AddNewAddrBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.AddNewAddrPresenterInter;
import lizhi.bwie.com.jingdongcom.view.inter.AddNewAddrInter;

/**
 * author:Created by Basic on 2018/3/28.
 */

public class AddNewAddrPresenter implements AddNewAddrPresenterInter {

    private AddNewAddrInter addNewAddrInter;
    private AddNewAddrModel addNewAddrModel;

    public AddNewAddrPresenter(AddNewAddrInter addNewAddrInter) {
        this.addNewAddrInter = addNewAddrInter;
        addNewAddrModel = new AddNewAddrModel(this);
    }

    public void addNewAddr(String addNewAddrUrl, String uid, String addr, String phone, String name) {

        addNewAddrModel.addNewAddr(addNewAddrUrl,uid,addr,phone,name);
    }

    @Override
    public void onAddAddrSuccess(AddNewAddrBean addNewAddrBean) {
        addNewAddrInter.onAddNewAddrSuccess(addNewAddrBean);
    }
}
