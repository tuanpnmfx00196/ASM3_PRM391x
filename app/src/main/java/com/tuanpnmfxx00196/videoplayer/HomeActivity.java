package com.tuanpnmfxx00196.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class HomeActivity extends AppCompatActivity{
    public static String API_KEY ="AIzaSyDiP3vYI24TRxfxI3iIrDblzd8t892XV3g";
    String ID_PPLAYLIST ="PLabC8q982sgHCKaqN7IPlDyaEt03bM69S";
    String urlGetJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ID_PPLAYLIST+
            "&key="+API_KEY+"&maxResults=10";
    String TITLE_ACTIONBAR = "Welcome to Funix";
    ListView listViewVideo;
    ArrayList<VideoYoutube>arrayList;
    VideoAdapter adapter;
    HistoryDB historyDB;
    ArrayList<VideoYoutube>arrayListHistory;
    ArrayList<VideoYoutube>arrayListHistory1;
    Bundle bundle;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hi "+getUser()+" "+TITLE_ACTIONBAR);
        actionBar.show();
           FragmentManager fragmentManager = getSupportFragmentManager();
           FragmentPlayList fragmentPlayList = new FragmentPlayList();
           bundle = new Bundle();
           bundle.putString("URLGETJSON",urlGetJson);
           bundle.putString("USER",getUser());
            fragmentPlayList.setArguments(bundle);
           FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
           fragmentTransaction.add(R.id.frameLayout,fragmentPlayList);
           fragmentTransaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
           switch (item.getItemId()) {
               case R.id.history:
                   ShowHistory();

                   break;
               case R.id.logOut:
                   LogOut();
                   break;
           }
           return true;
    }
    private String getUser(){
           String user;
           Intent intent = getIntent();
           user = intent.getStringExtra("User");
           return user;
    }
    public void ShowHistory(){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentHistory fragmentHistory = new FragmentHistory();
            bundle = new Bundle();
            bundle.putString("USER",getUser());
            fragmentHistory.setArguments(bundle);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout,fragmentHistory);
            fragmentTransaction.commit();
    }
    public void LogOut(){
        Toast.makeText(HomeActivity.this, "Log Out", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}