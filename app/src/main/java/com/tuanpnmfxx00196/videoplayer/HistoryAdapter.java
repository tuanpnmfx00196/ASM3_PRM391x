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

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private int layoutHistory;
    private List<VideoYoutube> VideoHistory;

    public HistoryAdapter(Context context, int layoutHistory, List<VideoYoutube> videoHistory) {
        this.context = context;
        this.layoutHistory = layoutHistory;
        VideoHistory = videoHistory;
    }

    @Override
    public int getCount() {
        return VideoHistory.size();
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
        VideoAdapter.ViewHolder holder;
        if(convertView==null){
            holder = new VideoAdapter.ViewHolder();
            LayoutInflater inflater = ( LayoutInflater ) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutHistory,null);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.textViewTitle);
            holder.imgThumbnail = (ImageView)convertView.findViewById(R.id.imageViewThumbnail);
            convertView.setTag(holder);
        }
        else{
            holder = (VideoAdapter.ViewHolder) convertView.getTag();
        }
        VideoYoutube videoYoutube = VideoHistory.get(position);
        holder.txtTitle.setText(videoYoutube.getTitleVideo());
        Picasso.get().load(videoYoutube.getThumbnail()).into(holder.imgThumbnail);
        return convertView;
    }
}
