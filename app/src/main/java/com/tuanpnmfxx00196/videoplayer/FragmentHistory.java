package com.tuanpnmfxx00196.videoplayer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentHistory extends Fragment {
    HistoryDB historyDB;
    ArrayList<VideoYoutube>arrayListHistory;
    ArrayList<VideoYoutube>arrayListHistory1;
    ListView historyList;
    VideoAdapter adapter;
    String user ="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frafment_history,container,false);
        Bundle bundle = getArguments();
        user = bundle.getString("USER");
        GetArrayListHistory();
        GetDataByUser(user);
        historyList = (ListView)rootView.findViewById(R.id.historyList);
        adapter = new VideoAdapter(getActivity(), R.layout.row_video,arrayListHistory);
        historyList.setAdapter(adapter);

        return  rootView;
    }
    /*============================= CLICK ITEM LISTENER ================================*/
    @Override
    public void onStart() {
        super.onStart();
        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),PlayVideo.class);
                intent.putExtra("IdVideo",arrayListHistory.get(position).getIdVideo());
                startActivity(intent);
            }
        });

    }
    public void GetArrayListHistory(){
        historyDB = new HistoryDB(getContext());
        arrayListHistory1 = new ArrayList<VideoYoutube>();
        Cursor res = historyDB.GetAllData();
        if(res.getCount()==0){
            Toast.makeText(getContext(),"No data",Toast.LENGTH_SHORT).show();
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
    /*======================== GET HISTORY VIDEo BY USER NAME =======================*/
    public void GetDataByUser(String user){
        arrayListHistory = new ArrayList<VideoYoutube>();
        for(int i=0; i<arrayListHistory1.size();i++){
            if(arrayListHistory1.get(i).getUser().equals(user)){
                arrayListHistory.add(arrayListHistory1.get(i));
            }
        }
    }
}
