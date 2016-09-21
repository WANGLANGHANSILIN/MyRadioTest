package com.shenbao.radiotest.radiotest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RadioAdapter extends BaseAdapter {

    private ArrayList<RadioEntity> radioInfoEntities;
    private Context context;
    public RadioAdapter(Context context, ArrayList<RadioEntity> radioInfoEntities){
        this.radioInfoEntities = radioInfoEntities;
        this.context = context;
    }

    @Override
    public int getCount() {
        return radioInfoEntities.size();
    }

    @Override
    public RadioEntity getItem(int position) {
        return radioInfoEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

     @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodle hodle = null;
        if (convertView == null){
            convertView = View.inflate(context,R.layout.lv_radionnfoitem,null);
            hodle = new ViewHodle();
            hodle.imageView = (ImageView) convertView.findViewById(R.id.iv_radio_icon);
            hodle.textView = (TextView) convertView.findViewById(R.id.tv_radioname);
            convertView.setTag(hodle);
        }else
        {
            hodle = (ViewHodle) convertView.getTag();
        }
        hodle.textView.setText(getItem(position).getRadioName());
        return convertView;
    }
    class ViewHodle{
        TextView textView;
        ImageView imageView;
    }
}