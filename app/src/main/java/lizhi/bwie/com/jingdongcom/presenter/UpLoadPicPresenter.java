package lizhi.bwie.com.jingdongcom.presenter;

import java.io.File;

import lizhi.bwie.com.jingdongcom.model.UpLoadPicModel;
import lizhi.bwie.com.jingdongcom.model.bean.UpLoadPicBean;
import lizhi.bwie.com.jingdongcom.presenter.inter.UpLoadPicPresenterInter;
import lizhi.bwie.com.jingdongcom.view.inter.UpLoadActivityInter;

/**
 * author:Created by Basic on 2018/3/28.
 */

public class UpLoadPicPresenter implements UpLoadPicPresenterInter {

    private UpLoadPicModel upLoadPicModel;
    private UpLoadActivityInter upLoadActivityInter;

    public UpLoadPicPresenter(UpLoadActivityInter upLoadActivityInter) {
        this.upLoadActivityInter = upLoadActivityInter;
        upLoadPicModel = new UpLoadPicModel(this);
    }

    public void uploadPic(String uploadIconUrl, File saveIconFile, String uid, String fileName) {

        upLoadPicModel.uploadPic(uploadIconUrl,saveIconFile,uid,fileName);

    }

    @Override
    public void uploadPicSuccess(UpLoadPicBean upLoadPicBean) {
        upLoadActivityInter.uploadPicSuccess(upLoadPicBean);
    }
}
