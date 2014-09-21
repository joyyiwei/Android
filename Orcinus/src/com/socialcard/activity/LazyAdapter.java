package com.socialcard.activity;

/**
 * Created by acer on 14-5-2.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by acer on 14-5-2.
 */
public class LazyAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    //public ImageLoader imageLoader; //用来下载图片的类，后面有介绍

    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //    imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.activity_export_row, null);
/*
        TextView title = (TextView)vi.findViewById(R.id.title); // 标题
        TextView artist = (TextView)vi.findViewById(R.id.artist); // 歌手名
        TextView duration = (TextView)vi.findViewById(R.id.duration); // 时长
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // 缩略图
*/
        TextView  name = (TextView)vi.findViewById(R.id.listview_name);
        ImageView photo = (ImageView)vi.findViewById(R.id.list_image);

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        // 设置ListView的相关值
 /*       title.setText(song.get(CustomizedListView.KEY_TITLE));
        artist.setText(song.get(CustomizedListView.KEY_ARTIST));
        duration.setText(song.get(CustomizedListView.KEY_DURATION));
        String url = song.get(CustomizedListView.KEY_THUMB_URL);
        imageLoader.DisplayImage(url, thumb_image);
*/
        name.setText(song.get("name"));
        return vi;
    }
}
