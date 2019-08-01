package com.tuanpnmfxx00196.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentPlayList extends Fragment{
    String user;
    String urlGetJson;
    ArrayList<VideoYoutube>arrayList;
    ListView listViewVideo;
    VideoAdapter adapter;
    Bundle bundle;
    HistoryDB historyDB;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_play_list,container,false);
        //get data from Activity
        bundle=getArguments();
        user = bundle.getString("USER");
        urlGetJson = bundle.getString("URLGETJSON");
        arrayList = new ArrayList<VideoYoutube>();
        GetJsonYoutube(urlGetJson);
        //Create listview
        listViewVideo = (ListView)rootView.findViewById(R.id.listViewVideo);
        adapter = new VideoAdapter(getActivity(),R.layout.row_video,arrayList);
        listViewVideo.setAdapter(adapter);
        return  rootView;
    }
    /*============================ITEMCLICKLISTENER===============================*/
    @Override
    public void onStart() {
        super.onStart();
        listViewVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),PlayVideo.class);
                intent.putExtra("IdVideo",arrayList.get(position).getIdVideo());
                historyDB = new HistoryDB(getActivity());
                historyDB.insertData(user,arrayList.get(position).getTitleVideo(),
                        arrayList.get(position).getDescription(),
                        arrayList.get(position).getThumbnail(),arrayList.get(position).getIdVideo());
                startActivity(intent);
            }
        });
    }
    /*============================GET JSON OBJECT YOUTUBE==============================*/
    private void GetJsonYoutube(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String title="";
                    String description="";
                    String url="";
                    String idVideo="";
                    JSONArray jsonItems = response.getJSONArray("items");
                    for(int i=0; i<jsonItems.length();i++){
                        JSONObject jsonItem = jsonItems.getJSONObject(i);
                        JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                        title = jsonSnippet.getString("title");
                        description = jsonSnippet.getString("description");
                        JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                        JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                        url = jsonMedium.getString("url");
                        JSONObject jsonResourceId = jsonSnippet.getJSONObject("resourceId");
                        idVideo = jsonResourceId.getString("videoId");
                        //Toast.makeText(HomeActivity.this, url,Toast.LENGTH_SHORT).show();
                        arrayList.add(new VideoYoutube(user,title,description, url,idVideo));
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error getJson",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}
