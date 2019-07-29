package com.tuanpnmfxx00196.videoplayer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


public class History extends AppCompatActivity {
    HistoryDB historyDB;
    ArrayList<VideoYoutube>arrayListHistory;
    ArrayList<VideoYoutube>arrayListHistory1;
    ListView lvHistory;
    VideoAdapter adapter;
    String user ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent = getIntent();
        user = intent.getStringExtra("User");
        GetArrayListHistory();
        GetDataByUser(user);
        lvHistory = (ListView)findViewById(R.id.listViewHistory);
        adapter = new VideoAdapter(History.this, R.layout.row_video,arrayListHistory);
        lvHistory.setAdapter(adapter);
    }
    public void GetArrayListHistory(){
        historyDB = new HistoryDB(this);
        arrayListHistory1 = new ArrayList<VideoYoutube>();
        Cursor res = historyDB.GetAllData();
        if(res.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                    VideoYoutube videoYoutube = new VideoYoutube(res.getString(1),res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getString(5)
                    );
                    arrayListHistory1.add(videoYoutube);
                    res.moveToNext();
            }
        }
    }
    public void GetDataByUser(String user){
        arrayListHistory = new ArrayList<VideoYoutube>();
        for(int i=0; i<arrayListHistory1.size();i++){
            if(arrayListHistory1.get(i).getUser().equals(user)){
                arrayListHistory.add(arrayListHistory1.get(i));
            }
        }
    }
}
