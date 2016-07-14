package com.example.dell.v2ex.AsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dell on 2016/7/11.
 */

public class DownloadImageTask extends AsyncTask<String,Integer,Bitmap> {

    private String ImageUrl;
    private ListView listView;
    private ImageView imageView;

    public DownloadImageTask( ListView listView){ //把前面我们获取的Item的View实例传进来，好在这般对应
        this.listView = listView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        ImageUrl =strings[0];

        URL url;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        Bitmap bitmap = null;
        try {
            url = new URL(ImageUrl);
            connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.connect();

            inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            //inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            if(inputStream !=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageView = (ImageView)listView.findViewWithTag(ImageUrl);
        if(bitmap!=null & imageView !=null){
            imageView.setImageBitmap(bitmap);
        }
    }
}
