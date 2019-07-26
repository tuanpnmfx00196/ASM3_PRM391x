package com.tuanpnmfxx00196.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
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

public class HomeActivity extends AppCompatActivity {
    public static String API_KEY ="AIzaSyDiP3vYI24TRxfxI3iIrDblzd8t892XV3g";
    String ID_PPLAYLIST ="PLabC8q982sgHCKaqN7IPlDyaEt03bM69S";
    String urlGetJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ID_PPLAYLIST+
            "&key="+API_KEY+"&maxResults=10";
    String TITLE_ACTIONBAR = "Welcome to Funix";
    ListView listViewVideo;
    ArrayList<VideoYoutube>arrayList;
    VideoAdapter adapter;
    HistoryDB historyDB;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hi "+getUser()+" "+TITLE_ACTIONBAR);
        actionBar.show();
        GetJsonYoutube(urlGetJson);
        listViewVideo = (ListView)findViewById(R.id.listViewVideo);
        arrayList = new ArrayList<>();
        adapter = new VideoAdapter(HomeActivity.this, R.layout.row_video,arrayList);
        listViewVideo.setAdapter(adapter);
        listViewVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this,PlayVideo.class);
                intent.putExtra("IdVideo",arrayList.get(position).getIdVideo());
                historyDB = new HistoryDB(HomeActivity.this);
                historyDB.insertData(getUser(),arrayList.get(position).getIdVideo());
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void GetJsonYoutube(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String title="";
                    String url="";
                    String idVideo="";
                    JSONArray jsonItems = response.getJSONArray("items");
                    for(int i=0; i<jsonItems.length();i++){
                        JSONObject jsonItem = jsonItems.getJSONObject(i);
                        JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                        title = jsonSnippet.getString("title");
                        JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                        JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                        url = jsonMedium.getString("url");
                        JSONObject jsonResourceId = jsonSnippet.getJSONObject("resourceId");
                        idVideo = jsonResourceId.getString("videoId");
                        //Toast.makeText(HomeActivity.this, url,Toast.LENGTH_SHORT).show();
                        arrayList.add(new VideoYoutube(title,url,idVideo));
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
                        Toast.makeText(HomeActivity.this, "Error getJson",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
    private String getUser(){
           String user;
           Intent intent = getIntent();
           user = intent.getStringExtra("User");
           return user;
    }
}
