package com.example.dell.v2ex.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
    private ListView fragmentList;
    private final String Url = "https://www.v2ex.com/api/topics/latest.json";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutView = inflater.inflate(R.layout.fragmentlayout,container,false);

        fragmentList = (ListView)LayoutView.findViewById(R.id.fragmentList);

        ArrayList<HashMap<String,String>> contentList = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();

        JsonHelper jsonHelper = new JsonHelper(Url);
        List<JsonData> datas = jsonHelper.getData();

        for(JsonData jsondata : datas){


        }


        return LayoutView;
    }

}
