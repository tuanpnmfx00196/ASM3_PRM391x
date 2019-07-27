package com.tuanpnmfxx00196.videoplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.AdapterView;

public class HistoryDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "History.sqlite";
    public static final String TABLE_NAME = "History_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USER";
    public static final String COL_3 = "KEYVIDEO";

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
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USER TEXT, KEYVIDEO INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String userName, int keyVideo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, userName);
        contentValues.put(COL_3, keyVideo);
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
    public boolean Update(String id, String userName, int keyVideo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, userName);
        contentValues.put(COL_3, keyVideo);
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
}
