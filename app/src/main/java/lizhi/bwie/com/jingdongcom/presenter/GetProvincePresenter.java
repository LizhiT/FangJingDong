package lizhi.bwie.com.jingdongcom.presenter;

import android.content.Context;

import java.util.List;

import lizhi.bwie.com.jingdongcom.model.GetProvinceModel;
import lizhi.bwie.com.jingdongcom.model.bean.ProvinceBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.GetProvincePresenterInter;
import lizhi.bwie.com.jingdongcom.view.inter.GetProvinceInter;

/**
 * author:Created by Basic on 2018/3/28.
 */

public class GetProvincePresenter implements GetProvincePresenterInter {

    private GetProvinceInter getProvinceInter;
    private GetProvinceModel getProvinceModel;

    public GetProvincePresenter(GetProvinceInter getProvinceInter) {
        this.getProvinceInter = getProvinceInter;
        getProvinceModel = new GetProvinceModel(this);
    }

    public void getProvince(Context context) {
        getProvinceModel.getProvince(context);
}

    @Override
    public void onGetProvince(List<ProvinceBean> list) {
        getProvinceInter.onGetProvince(list);
    }
}
