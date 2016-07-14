package com.example.dell.v2ex.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dell.v2ex.Adapter.ContentAdapter;
import com.example.dell.v2ex.CallBack.JsonDataCallBack;
import com.example.dell.v2ex.JSON.JsonData;
import com.example.dell.v2ex.JSON.JsonHelper;
import com.example.dell.v2ex.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dell on 2016/7/10.
 */

public class AllFragment extends Fragment{
    private View LayoutView;
    private View Layout;
    private ListView fragmentList;
    private JsonHelper jsonHelper;
    private ContentAdapter contentAdapter;

    List<JsonData> datas;
    HashMap<String,String> map = new HashMap<>();
    ArrayList<HashMap<String,String>> contentList = new ArrayList<>();

    private final String Url = "https://www.v2ex.com/api/topics/hot.json";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutView = inflater.inflate(R.layout.fragmentlayout,container,false);

        fragmentList = (ListView)LayoutView.findViewById(R.id.fragmentList);
        Log.e("test","1");

        Log.e("test","2");
        jsonHelper = new JsonHelper(Url, new JsonDataCallBack() {
            @Override
            public void onRespone(String response) {
                datas =jsonHelper.getData(response);
                for(JsonData jsondata : datas){   //相当于用JsonHelper类解析一次之后把数据都储存在map中.
                    Log.e("testFor","test");
                    map.put("id",jsondata.getId());
                    map.put("title",jsondata.getTitle());
                    map.put("content",jsondata.getContent());
                    map.put("content_rendered",jsondata.getContent_rendered());
                    map.put("replies",jsondata.getReplies());

                    map.put("member_id",jsondata.getMember_id());
                    map.put("member_username",jsondata.getMember_username());
                    map.put("member_tagline",jsondata.getMember_tagline());
                    map.put("member_avatar_mini",jsondata.getMember_avatar_mini());
                    map.put("member_avatar_normal",jsondata.getMember_avatar_normal());
                    map.put("member_avatar_large",jsondata.getMember_avatar_large());

                    map.put("node_id",jsondata.getNode_id());
                    map.put("node_name",jsondata.getNode_name());
                    map.put("node_title",jsondata.getNode_title());
                    map.put("node_title_alternative",jsondata.getNode_title_alternative());
                    map.put("node_topics",jsondata.getNode_topics());
                    map.put("node_avartar_mini",jsondata.getNode_avartar_mini());//这里的图片实际上都是直接获取的URL
                    map.put("node_avartar_normal",jsondata.getNode_avartar_normal());
                    map.put("node_avatar_large",jsondata.getNode_avatar_large());

                    map.put("created",jsondata.getCreated());
                    map.put("last_modified",jsondata.getLast_modified());
                    map.put("last_touched",jsondata.getLast_touched());

                    map.put("url",jsondata.getUrl());
                    map.put("node_url",jsondata.getNode_url());

                    contentList.add(map);

                    contentAdapter = new ContentAdapter(getActivity(),contentList);
                    container.post(new Runnable() {
                        @Override
                        public void run() {
                            fragmentList.setAdapter(contentAdapter);
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
        Log.e("test","3");
        //List<JsonData> datas = jsonHelper.getData(null);//取得所有解析的数据
        //Log.e("testBack",datas.toString());

        if(LayoutView !=null){
            Layout= LayoutView;
        }

        return Layout;
    }

}
