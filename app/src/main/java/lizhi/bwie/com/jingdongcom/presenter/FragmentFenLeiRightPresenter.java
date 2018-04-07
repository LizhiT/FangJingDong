package lizhi.bwie.com.jingdongcom.presenter;

import lizhi.bwie.com.jingdongcom.model.FragmentFenLeiRightModel;
import lizhi.bwie.com.jingdongcom.model.bean.ChildFenLeiBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.FenLeiRightPresenterInter;
import lizhi.bwie.com.jingdongcom.view.inter.FenLeiRightInter;

/**
 * author:Created by Basic on 2018/3/27.
 */

public class FragmentFenLeiRightPresenter implements FenLeiRightPresenterInter {

    private FenLeiRightInter fenLeiRightInter;
    private FragmentFenLeiRightModel fragmentFenLeiRightModel;

    public FragmentFenLeiRightPresenter(FenLeiRightInter fenLeiRightInter) {
        this.fenLeiRightInter = fenLeiRightInter;

        fragmentFenLeiRightModel = new FragmentFenLeiRightModel(this);
    }

    public void getChildData(String childFenLeiUrl, int cid) {

        fragmentFenLeiRightModel.getChildData(childFenLeiUrl,cid);
    }

    @Override
    public void onSuncess(ChildFenLeiBean childFenLeiBean) {

        fenLeiRightInter.getSuccessChildData(childFenLeiBean);

    }
}
