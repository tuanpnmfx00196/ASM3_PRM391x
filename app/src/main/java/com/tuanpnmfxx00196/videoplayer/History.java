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
    ListView lvHistory;
    VideoAdapter adapter;
    ArrayList<VideoYoutube> arrTemp;
    String user ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        GetArrayListHistory();
        lvHistory = (ListView)findViewById(R.id.listViewHistory);
        adapter = new VideoAdapter(History.this, R.layout.row_video,arrayListHistory);
        lvHistory.setAdapter(adapter);
    }
    public void GetArrayListHistory(){
        Intent intent = getIntent();
        user = intent.getStringExtra("User");
        historyDB = new HistoryDB(this);
        arrayListHistory = new ArrayList<VideoYoutube>();
        Cursor res = historyDB.GetAllData();
        if(res.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                if (res.getString(1).equals(user)) {
                    VideoYoutube videoYoutube = new VideoYoutube(res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getString(5)
                    );
                    arrayListHistory.add(videoYoutube);
                }
                else{
                    res.moveToNext();
                }
            }
        }
//        arrTemp = new ArrayList<VideoYoutube>();
//        for(int i=0;i<arrayListHistory.size(); i++) {
//            if (!arrTemp.contains(arrayListHistory)){
//                arrTemp.add(arrayListHistory.get(i));
//            }
        //arrayListHistory.clear();
        //arrayListHistory.addAll(arrTemp);
    }
}
