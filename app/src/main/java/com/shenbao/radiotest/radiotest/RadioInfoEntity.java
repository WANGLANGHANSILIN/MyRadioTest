package com.shenbao.radiotest.radiotest;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangl on 2016/9/19 0019.
 */
public class RadioInfoEntity implements Serializable{

    private String radioType;
    private String radioCount;
    private String radioBelongs;
    private ArrayList<RadioEntity> radioEntityList = new ArrayList<>();


    //获取当前的电台的集合
    public ArrayList<RadioEntity> getRadioEntityList() {
        return radioEntityList;
    }

    //添加电台到集合里
    public void addRadioEntityList(RadioEntity radioEntity) {
        this.radioEntityList.add(radioEntity);
    }

    public String getRadioType() {
        return radioType;
    }

    public void setRadioType(String radioType) {
        this.radioType = radioType;
    }

    public String getRadioCount() {
        return radioCount;
    }

    public void setRadioCount(String radioCount) {
        this.radioCount = radioCount;
    }

    public String getRadioBelongs() {
        return radioBelongs;
    }

    public void setRadioBelongs(String radioBelongs) {
        this.radioBelongs = radioBelongs;
    }

    @Override
    public String toString() {
        return "RadioInfo{" +
                "radioType='" + radioType + '\'' +
                ", radioCount='" + radioCount + '\'' +
                ", radioListCount='" + radioEntityList.size() + '\'' +
                ", radioBelongs='" + radioBelongs + '\'' +
                '}';
    }
}
