package com.shenbao.radiotest.radiotest.costomView;

/**
 * Created by wangl on 2016/9/20 0020.
 */
public class DataClass {
    private String Name;//名称
    private float Value;//数组
    private float Percentage;//百分比

    private int color;//颜色
    private float angle;//角度

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getValue() {
        return Value;
    }

    public void setValue(float value) {
        Value = value;
    }

    public float getPercentage() {
        return Percentage;
    }

    public void setPercentage(float percentage) {
        Percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
