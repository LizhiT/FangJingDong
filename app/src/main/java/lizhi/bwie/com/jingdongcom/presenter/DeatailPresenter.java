package lizhi.bwie.com.jingdongcom.presenter;

import lizhi.bwie.com.jingdongcom.model.DeatilModel;
import lizhi.bwie.com.jingdongcom.model.bean.DeatilBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.DeatilPresenterInter;
import lizhi.bwie.com.jingdongcom.view.inter.DetailActivityInter;

/**
 * author:Created by Basic on 2018/3/27.
 */

public class DeatailPresenter implements DeatilPresenterInter {

    private DeatilModel deatilModel;
    private DetailActivityInter detailActivityInter;

    public DeatailPresenter(DetailActivityInter detailActivityInter) {
        this.detailActivityInter = detailActivityInter;

        deatilModel = new DeatilModel(this);

    }

    public void getDetailData(String detailUrl, int pid) {

        deatilModel.getDetailData(detailUrl,pid);
    }

    @Override
    public void onSuccess(DeatilBean deatilBean) {
        //回调给view
        detailActivityInter.onSuccess(deatilBean);
    }
}
