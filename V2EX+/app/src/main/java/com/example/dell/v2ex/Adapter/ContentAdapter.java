package com.example.dell.v2ex.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dell.v2ex.AsyncTask.DownloadImageTask;
import com.example.dell.v2ex.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dell on 2016/7/11.
 */

public class ContentAdapter extends BaseAdapter {
    private ArrayList<HashMap<String,String>> contentList;
    private Context context;
    private static LayoutInflater inflater = null;
    private ListView ImageListView;

    public ContentAdapter(Context context,ArrayList<HashMap<String,String>> contentList){
        this.contentList = contentList;
        this.context = context;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contentList.size();
    }

    @Override
    public Object getItem(int position) {
        return contentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if(ImageListView == null){
            ImageListView = (ListView)viewGroup;//这里的viewGroup就是每一条Item的实例，这里讲他强制转化为ListView类
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.itemlayout,null);
        }

        ImageView user_image = (ImageView)convertView.findViewById(R.id.user_image);
        TextView user_name = (TextView)convertView.findViewById(R.id.use_name);
        TextView node_tx = (TextView)convertView.findViewById(R.id.node);
        TextView replies_tx = (TextView)convertView.findViewById(R.id.replies_tx);
        TextView put_time = (TextView)convertView.findViewById(R.id.put_time);
        TextView content_tx =(TextView)convertView.findViewById(R.id.content_text);

        HashMap<String,String> ContentData = contentList.get(position);//获取每个位置上对应的数据
        Log.e("test_ContentData",ContentData.toString());
        //Log.e("test_contentList",contentList.toString());

        user_name.setText(ContentData.get("member_username"));
        node_tx.setText(ContentData.get("node_name"));
        replies_tx.setText(ContentData.get("replies"));
        content_tx.setText(ContentData.get("content"));

        DownloadImageTask downloadImageTask = new DownloadImageTask(ImageListView);
        downloadImageTask.execute("http:"+ContentData.get("member_avatar_large"));//必须加“http”,否则识别不了网页.

        user_image.setTag("http:"+ContentData.get("member_avatar_large"));

        return convertView;
    }
}
