package com.shenbao.radiotest.radiotest;

import android.os.Message;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by wangl on 2016/9/19 0019.
 */
public class NetRequstUtils {

//    private NetRequstUtils requstUtils = null;

    private NetRequstUtils(){}

    private static class NetUtilHodle{
        private static NetRequstUtils requstUtils = new NetRequstUtils();
    }

    public static NetRequstUtils getInstace(){
        return NetUtilHodle.requstUtils;
    }

    public void connectRequest(String url)
    {
        RequstRunnable runnable = new RequstRunnable(url);
        new Thread(runnable).start();
//        return runnable.getRadioInfoEntities();
    }

    private class RequstRunnable implements Runnable{
        String url;
        ArrayList<RadioInfoEntity> radioInfoEntities;
        public RequstRunnable(String url)
        {
            this.url = url;
            radioInfoEntities = new ArrayList<>();
        }
        @Override
        public void run() {
            try {
//                radioInfoEntities.addAll();
                ArrayList<RadioInfoEntity> infoEntityArrayList = requestNet(url);
                if (infoEntityArrayList != null)
                {
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = infoEntityArrayList;
                    MainActivity.handler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        public ArrayList<RadioInfoEntity> getRadioInfoEntities()
        {
            if (radioInfoEntities == null)
            {
                try {
                    radioInfoEntities.addAll(requestNet(url));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
            return radioInfoEntities;
        }
    }

    private ArrayList<RadioInfoEntity> requestNet(String url) throws IOException, XmlPullParserException
    {
        URL url1 = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        connection.setConnectTimeout(8000);
        connection.setRequestMethod("get");
        ArrayList<RadioInfoEntity> infoEntityArrayList = new ArrayList<>();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
        {
            InputStream stream = connection.getInputStream();

//            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//
//            StringWriter writer = new StringWriter();
//
//            String line = "";
//
//            while ((line = reader.readLine()) != null){
//                writer.write(line);
//            }
//            测试代码
            // TODO: 2016/9/20 0020 功能还有待加强 

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = factory.newPullParser();
            int eventType = pullParser.getEventType();
            Log.i("RadioEntity","eventType:"+eventType+"<<<---");
            while (XmlPullParser.END_DOCUMENT != eventType)
            {
                String pullParserName = "";
                if (eventType == XmlPullParser.START_TAG)
                {
                    pullParserName = pullParser.getName();
                    Log.i("RadioEntity","pullParserName:"+pullParserName+"<<<---");

                    if (pullParserName.equals("RadioInfo"))
                    {
                        RadioInfoEntity infoEntity = new RadioInfoEntity();
                        infoEntity.setRadioType(pullParser.getAttributeValue(0));
                        String RadioCount = pullParser.getAttributeValue(1);
                        infoEntity.setRadioCount(RadioCount);
                        infoEntity.setRadioBelongs(pullParser.getAttributeValue(2));
                        Log.i("RadioEntity"  ,"RadioInfoEntity--->"+  infoEntity.toString());
                        eventType = pullParser.getEventType();
                        Log.i("RadioEntity","eventType:"+eventType+"---");
                        if (eventType == XmlPullParser.START_TAG)
                        {
                            pullParserName = pullParser.getName();
                            Log.i("RadioEntity","pullParserName:"+pullParserName+"---");
                            if (pullParserName.equals("Radio"))
                            {
                                for (int i = 0; i < Integer.valueOf(RadioCount); i++)
                                {
                                    RadioEntity radioEntity = new RadioEntity();
                                    radioEntity.setRadioName(pullParser.getAttributeValue(0));
                                    radioEntity.setRadioAddress(pullParser.getAttributeValue(1));
                                    Log.i("RadioEntity"  , "RadioEntity--->" + radioEntity.toString());
                                    infoEntity.addRadioEntityList(radioEntity);
                                }
                            }
                        }
                        infoEntityArrayList.add(infoEntity);
                    }
                }
                pullParser.next();
            }
        }
        return infoEntityArrayList;
    }
}
