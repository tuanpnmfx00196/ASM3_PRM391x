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
    ListView listHistory;
    HistoryAdapter historyAdapter;
    ArrayList<VideoYoutube> arrayList1;
    ArrayList<Integer>arrayList2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent = getIntent();
        ArrayList<VideoYoutube> arrayList = (ArrayList<VideoYoutube>) intent.getSerializableExtra("ArrayList");
        Toast.makeText(History.this,arrayList.size()+" h",Toast.LENGTH_SHORT).show();
        listHistory = (ListView) findViewById(R.id.listHistory);
        historyAdapter = new HistoryAdapter(History.this, R.layout.row_video, arrayList1);
        listHistory.setAdapter(historyAdapter);
    }
    public void GetArrayList() {
        historyDB = new HistoryDB(this);
        arrayList2 = new ArrayList<Integer>();
        Cursor res = historyDB.GetAllData();
        if (res.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                arrayList2.add(res.getInt(2));
                res.moveToNext();
            }
        }
    }
}
