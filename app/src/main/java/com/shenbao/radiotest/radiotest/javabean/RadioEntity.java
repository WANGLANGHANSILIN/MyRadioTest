package com.shenbao.radiotest.radiotest.javabean;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by wangl on 2016/9/19 0019.
 */
public class RadioEntity implements Serializable {
    private String radioName;
    private String radioAddress;


    public String getRadioName() {
        return radioName;
    }

    public void setRadioName(String radioName) {
        this.radioName = radioName;
    }

    public String getRadioAddress() {
        return radioAddress;
    }

    public void setRadioAddress(String radioAddress) {
        this.radioAddress = radioAddress;
    }

    @Override
    public String toString() {
        return "RadioEntity{" +
                "radioName='" + radioName + '\'' +
                ", radioAddress='" + radioAddress + '\'' +
                '}';
    }
}
