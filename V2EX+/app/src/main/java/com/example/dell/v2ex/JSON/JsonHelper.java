package com.example.dell.v2ex.JSON;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.dell.v2ex.CallBack.JsonDataCallBack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/7/10.
 * 开子线程获取网络数据，抓取所有节点下的JSON数据
 */

public class JsonHelper {
    private String urlPath;
    private JsonData jsonData;
    private Bundle JsonData;
    //private JsonDataCallBack callBack;
    private List<JsonData> jsonDatas;

    /*
    private Handler handler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Bundle getData = msg.getData();
                    String jsonString =  getData.getString("text");
                    Log.e("testJsonGet","jsonString");
                    getData(jsonString);
            }
        }
    };
    */

    public JsonHelper(final String urlPath, final JsonDataCallBack callBack){//通过指定URL读取数据
        this.urlPath = urlPath;
        Log.e("testJsonHelper","JsonHelper");
        //getJsonString(urlPath);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.e("test","getJson1");
                    //String urlPath = url;
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                    Log.e("test","getJson2");
                    URL murl = new URL(urlPath);

                    Log.e("test","getJson3");
                    HttpURLConnection connection = (HttpURLConnection)murl.openConnection();

                    Log.e("test","getJson4");
                    InputStream inputStream = connection.getInputStream();

                    Log.e("test","getJson5");
                    byte[] data = new byte[1024];
                    int length = -1;
                    while((length = inputStream.read(data))!=-1){
                        outputStream.write(data,0,length);
                    }
                    //passJsonData(outputStream.toString());
                    //Log.e("testOutputStream",outputStream.toString());
                    callBack.onRespone(outputStream.toString());

                    inputStream.close();

                }catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }).start();
    }


    public List<JsonData> getData(String jsonString){
        jsonDatas = new ArrayList<>();
        jsonData = new JsonData();

        try{
            Log.e("TestJsonString",jsonString);
            JSONArray array = new JSONArray(jsonString);//最外层为[]，用JSONArray
            for(int i=0;i<array.length();i++){

                JSONObject object = array.getJSONObject(i);
                jsonData.setId(object.getString("id"));
                jsonData.setTitle(object.getString("title"));
                jsonData.setUrl(object.getString("url"));
                jsonData.setContent(object.getString("content"));
                jsonData.setContent_rendered(object.getString("content_rendered"));
                jsonData.setReplies(object.getString("replies"));

                JSONObject memberObject = object.getJSONObject("member");//number节点
                for(int j = 0;j<memberObject.length();j++){

                    //JSONObject memberObject = memberObject.getJSONObject(j);
                    jsonData.setMember_id(memberObject.getString("id"));
                    jsonData.setMember_username(memberObject.getString("username"));
                    jsonData.setMember_tagline(memberObject.getString("tagline"));
                    jsonData.setMember_avatar_mini(memberObject.getString("avatar_mini"));
                    jsonData.setMember_avatar_normal(memberObject.getString("avatar_normal"));
                    jsonData.setMember_avatar_large(memberObject.getString("avatar_large"));

                }

                JSONObject nodeObject = object.getJSONObject("node");//node节点
                for(int x = 0;x < nodeObject.length();x++){

                    //JSONObject nodeObject = nodeArray.getJSONObject(x);
                    jsonData.setNode_id(nodeObject.getString("id"));
                    jsonData.setNode_name(nodeObject.getString("name"));
                    jsonData.setNode_title(nodeObject.getString("title"));
                    jsonData.setNode_title_alternative(nodeObject.getString("title_alternative"));
                    jsonData.setNode_url(nodeObject.getString("url"));
                    jsonData.setNode_topics(nodeObject.getString("topics"));
                    jsonData.setNode_avartar_mini(nodeObject.getString("avatar_mini"));
                    jsonData.setNode_avartar_normal(nodeObject.getString("avatar_normal"));
                    jsonData.setNode_avatar_large(nodeObject.getString("avatar_large"));
                }

                jsonData.setCreated(object.getString("created"));
                jsonData.setLast_modified(object.getString("last_modified"));
                jsonData.setLast_touched(object.getString("last_touched"));

                jsonDatas.add(jsonData);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        Log.e("test_json","test");
        return jsonDatas;
    }



    /*
    private void getJsonString(final String url,final JsonDataCallBack callBack) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.e("test","getJson1");
                    String urlPath = url;
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                    Log.e("test","getJson2");
                    URL murl = new URL(urlPath);

                    Log.e("test","getJson3");
                    HttpURLConnection connection = (HttpURLConnection)murl.openConnection();

                    Log.e("test","getJson4");
                    InputStream inputStream = connection.getInputStream();

                    Log.e("test","getJson5");
                    byte[] data = new byte[1024];
                    int length = -1;
                    while((length = inputStream.read(data))!=-1){
                        outputStream.write(data,0,length);
                    }
                    //passJsonData(outputStream.toString());
                    //Log.e("testOutputStream",outputStream.toString());

                    inputStream.close();

                }catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }).start();
    }
    */

    /*
    public void passJsonData(String jsonData){
        Message msg = new Message();
        JsonData = new Bundle();

        JsonData.putString("text",jsonData);
        msg.what = 1;
        msg.setData(JsonData);

        Log.e("testHandler","testHandler");
        handler.sendMessage(msg);
    }
    */

}
