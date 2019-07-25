package com.tuanpnmfxx00196.videoplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<HomeActivity>VideoList;

    public VideoAdapter(Context context, int layout, List<HomeActivity> videoList) {
        this.context = context;
        this.layout = layout;
        VideoList = videoList;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        ImageView imgThumbnail;
        TextView txtTitle;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            LayoutInflater inflater = ( LayoutInflater ) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.textViewTitle);
            holder.imgThumbnail = (ImageView)convertView.findViewById(R.id.imageViewThumbnail);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        HomeActivity homeActivity = VideoList.get(position);
        holder.txtTitle.setText(homeActivity.getTitle());
        Picasso.get().load(homeActivity.getThumbnail()).into(holder.imgThumbnail);

        return convertView;
    }
}
