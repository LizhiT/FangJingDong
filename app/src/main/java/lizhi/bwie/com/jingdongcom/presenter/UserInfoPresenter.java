package lizhi.bwie.com.jingdongcom.presenter;

import lizhi.bwie.com.jingdongcom.model.bean.UserInfoBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.UserInforInter;
import lizhi.bwie.com.jingdongcom.view.inter.UserInfoPresenterInter;

/**
 * author:Created by Basic on 2018/3/28.
 */

public class UserInfoPresenter implements UserInfoPresenterInter {

    private final UserInfoModel userInfoModel;
    private final UserInforInter userInforInter;

    public UserInfoPresenter(UserInforInter userInforInter) {
        this.userInforInter = userInforInter;
        userInfoModel = new UserInfoModel(this);
    }

    public void getUserInfo(String userInfoUrl, String uid) {

        userInfoModel.getUserInfo(userInfoUrl,uid);

    }

    @Override
    public void onUserInfoSUccess(UserInfoBean userInfoBean) {
        userInforInter.onUserInforSuccess(userInfoBean);
    }
}
