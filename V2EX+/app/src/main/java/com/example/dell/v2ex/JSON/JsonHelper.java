package com.example.dell.v2ex.JSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/7/10.
 * 抓取所有节点下的JSON数据
 */

public class JsonHelper {
    private String urlPath;
    private JsonData jsonData;
    private List<JsonData> jsonDatas;

    public JsonHelper(String urlPath){
        this.urlPath = urlPath;
    }

    public List<JsonData> getData(){
        jsonDatas = new ArrayList<>();
        jsonData = new JsonData();

        try{
            String jsonString = getJsonString(urlPath);//通过指定URL读取数据

            JSONArray array = new JSONArray(jsonString);//最外层为[]，用JSONArray
            for(int i=0;i<array.length();i++){

                JSONObject object = array.getJSONObject(i);
                jsonData.setId(object.getString("id"));
                jsonData.setTitle(object.getString("title"));
                jsonData.setUrl(object.getString("url"));
                jsonData.setUrl(object.getString("content"));
                jsonData.setUrl(object.getString("content_rendered"));
                jsonData.setUrl(object.getString("replies"));

                JSONArray memberArray = object.getJSONArray("member");//number节点
                for(int j = 0;j<memberArray.length();j++){

                    JSONObject memberObject = memberArray.getJSONObject(j);
                    jsonData.setMember_id(memberObject.getString("id"));
                    jsonData.setMember_username(memberObject.getString("username"));
                    jsonData.setMember_tagline(memberObject.getString("tagline"));
                    jsonData.setMember_avatar_mini(memberObject.getString("avatar_mini"));
                    jsonData.setMember_avatar_normal(memberObject.getString("avatar_normal"));
                    jsonData.setMember_avatar_large(memberObject.getString("avatar_large"));
                }

                JSONArray nodeArray = object.getJSONArray("node");//node节点
                for(int x = 0;x < nodeArray.length();x++){

                    JSONObject nodeObject = nodeArray.getJSONObject(x);
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

        return jsonDatas;
    }

    private String getJsonString(String url) throws Exception {
        String urlPath = url;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        URL murl = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection)murl.openConnection();
        InputStream inputStream = connection.getInputStream();

        byte[] data = new byte[1024];
        int length = -1;
        while((length = inputStream.read(data))!=-1){
            outputStream.write(data,0,length);
        }
        inputStream.close();

        return new String(outputStream.toByteArray());
    }

}
