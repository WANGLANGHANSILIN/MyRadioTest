package com.shenbao.radiotest.radiotest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.shenbao.radiotest.radiotest.R;
import com.shenbao.radiotest.radiotest.adapter.RadioAdapter;
import com.shenbao.radiotest.radiotest.costomView.CircleView;
import com.shenbao.radiotest.radiotest.javabean.RadioEntity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wangl on 2016/9/19 0019.
 */
public class RadioActivity extends AppCompatActivity{

    private ListView radioShowListView;
    private CircleView circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_radioplay);
//
//        circleView = (CircleView) findViewById(R.id.circle);
//        ArrayList<DataClass> classes = new ArrayList<>();
//        for (int i = 0; i < classes.size(); i++) {
//            DataClass aClass = new DataClass();
//            aClass.setValue(i+5);
//            classes.add(aClass);
//        }
//        circleView.setDataClass(classes);
        Log.i("RadioInfoEntity","RadioActivity!!!!!");
        Log.i("RadioActivityIntent","RadioActivity!!!!!");
        Intent intent = getIntent();
//        Log.e("RadioActivityIntent",intent.getStringExtra("hello"));
        Bundle bundle = intent.getBundleExtra("bundle");
        Serializable radioArray = bundle.getSerializable("RadioArray");
//        RadioInfoEntity radioArray = (RadioInfoEntity) intent.getSerializableExtra("RadioArray");
        radioShowListView = (ListView) findViewById(R.id.lv_radio_show);
        radioShowListView.setAdapter(new RadioAdapter(RadioActivity.this, (ArrayList<RadioEntity>) radioArray));
    }
}
