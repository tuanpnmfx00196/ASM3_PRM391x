package com.tuanpnmfxx00196.videoplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.AdapterView;
import android.widget.Toast;

public class HistoryDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "History.sqlite";
    public static final String TABLE_NAME = "History_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USER";
    public static final String COL_3 = "TITLE";
    public static final String COL_4 = "THUMBNAIL";
    public static final String COL_5= "URL";

    public HistoryDB(Context context) {
        super(context, TABLE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    public void DataQuery(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " USER TEXT, TITLE TEXT, THUMBNAIL TEXT, URL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String userName, String titleVideo, String thumbail, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if(CheckData(userName,titleVideo,thumbail,url)) {
            contentValues.put(COL_2, userName);
            contentValues.put(COL_3, titleVideo);
            contentValues.put(COL_4, thumbail);
            contentValues.put(COL_5, url);
        }
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    public Cursor GetAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    public boolean Update(String id, String userName, String titleVideo, String thumbail, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, userName);
        contentValues.put(COL_3, titleVideo);
        contentValues.put(COL_4, thumbail);
        contentValues.put(COL_5, url);
        db.update(TABLE_NAME, contentValues, COL_2 + "=?", new String[]{id});
        db.close();

        return true;
    }
    public boolean Delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        db.delete(TABLE_NAME, COL_2 + "=?", new String[]{id});
        db.close();
        return true;
    }
    public boolean CheckData(String userName, String titleVideo, String thumbail, String url){
        int count=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        if(res.getCount()!=0){
            res.moveToFirst();
            while (res.isAfterLast()==false){
                if (res.getString(4).equals(url)) {
                    count++;
                }

                res.moveToNext();
            }
        }
        if(count!=0){
            return false;
        }
        else{
            return true;
        }

    }
}
