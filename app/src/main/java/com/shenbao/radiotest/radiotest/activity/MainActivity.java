package com.shenbao.radiotest.radiotest.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.shenbao.radiotest.radiotest.Constant;
import com.shenbao.radiotest.radiotest.utils.NetRequstUtils;
import com.shenbao.radiotest.radiotest.R;
import com.shenbao.radiotest.radiotest.javabean.RadioInfoEntity;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    private ListView radioInfoListView;
    private static ArrayList<RadioInfoEntity> radioInfoEntities;

    private static RadioInfoAdapter adapter;

    public static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    radioInfoEntities.addAll((Collection<? extends RadioInfoEntity>) msg.obj);
                    adapter.notifyDataSetChanged();
                    Log.i("MainActivity",""+radioInfoEntities.size());
                 break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        initView();
    }

    private void initData() {
        radioInfoEntities = new ArrayList<>();
        adapter = new RadioInfoAdapter();
        NetRequstUtils.getInstace().connectRequest(Constant.RadioUrl);
    }

    private void initView() {
        radioInfoListView = (ListView) findViewById(R.id.lv_radioList);

        radioInfoListView.setAdapter(adapter);
        radioInfoListView.setOnItemClickListener(new RadioInfoListener());
    }

    private class RadioInfoListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            RadioInfoEntity infoEntity = adapter.getItem(position);
            Log.e("RadioInfoEntity",""+infoEntity.toString()+"\n"+infoEntity.getRadioEntityList().toString());
            Intent intent = new Intent(MainActivity.this,RadioActivity.class);
//            intent.putExtra("hello","hello");
//            intent.putExtra("RadioArray",infoEntity.getRadioEntityList());
//            intent.putParcelableArrayListExtra("RadioArray", (ArrayList<? extends Parcelable>) infoEntity.getRadioEntityList());
            Bundle bundle = new Bundle();
            bundle.putSerializable("RadioArray", infoEntity.getRadioEntityList());
            intent.putExtra("bundle",bundle);
            startActivity(intent);
        }
    }

    private class RadioInfoAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return radioInfoEntities.size();
        }

        @Override
        public RadioInfoEntity getItem(int position) {
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
                convertView = View.inflate(MainActivity.this,R.layout.lv_radionnfoitem,null);
                hodle = new ViewHodle();
                hodle.imageView = (ImageView) convertView.findViewById(R.id.iv_radio_icon);
                hodle.textView = (TextView) convertView.findViewById(R.id.tv_radioname);
                convertView.setTag(hodle);
            }else
            {
                hodle = (ViewHodle) convertView.getTag();
            }
            hodle.textView.setText(getItem(position).getRadioType());
            return convertView;
        }
        class ViewHodle{
            TextView textView;
            ImageView imageView;
        }
    }
}
