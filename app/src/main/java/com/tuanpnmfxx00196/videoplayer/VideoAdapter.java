package com.tuanpnmfxx00196.videoplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<VideoYoutube> VideoList;

    public VideoAdapter(Context context, int layout, List<VideoYoutube> VideoList) {
        this.context = context;
        this.layout = layout;
        this.VideoList= VideoList;
    }

    @Override
    public int getCount() {
        return VideoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder{
        ImageView imgThumbnail;
        TextView tvDescription;
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
            holder.tvDescription = (TextView ) convertView.findViewById(R.id.tvDescription);
            holder.imgThumbnail = (ImageView)convertView.findViewById(R.id.imageViewThumbnail);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        VideoYoutube videoYoutube = VideoList.get(position);
        holder.txtTitle.setText(videoYoutube.getTitleVideo());
        holder.tvDescription.setText(videoYoutube.getDescription());
        Picasso.get().load(videoYoutube.getThumbnail()).into(holder.imgThumbnail);
        return convertView;
    }
}
