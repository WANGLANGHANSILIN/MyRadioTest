package com.shenbao.radiotest.radiotest;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.shenbao.radiotest.radiotest.costomView.CircleView;
import com.shenbao.radiotest.radiotest.costomView.DataClass;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wangl on 2016/9/19 0019.
 */
public class RadioActivity extends AppCompatActivity{

    private ListView radioShowListView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activit_radioplay);

        CircleView view = (CircleView) findViewById(R.id.circle);

        ArrayList<DataClass> classes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            DataClass dataClass = new DataClass();
            dataClass.setValue(8+i);
            classes.add(dataClass);
        }

        view.setDataClass(classes);
        view.setStartAngle(0);
//        Intent intent = getIntent();
//        RadioInfoEntity radioArray = (RadioInfoEntity) intent.getSerializableExtra("RadioArray");
//        radioShowListView = (ListView) findViewById(R.id.lv_radio_show);
//        radioShowListView.setAdapter(new RadioAdapter(RadioActivity.this,radioArray.getRadioEntityList()));
    }
}
