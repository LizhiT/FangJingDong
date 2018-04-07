package lizhi.bwie.com.jingdongcom.model.bean;

import java.io.Serializable;

/**
 * author:Created by Basic on 2018/3/28.
 */

public class ProvinceBean implements Serializable {
    private int regionid;
    private String name;

    public ProvinceBean(int regionid, String name) {
        this.regionid = regionid;
        this.name = name;
    }

    public int getRegionid() {
        return regionid;
    }

    public void setRegionid(int regionid) {
        this.regionid = regionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
