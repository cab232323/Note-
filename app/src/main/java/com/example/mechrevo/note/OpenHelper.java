package com.example.mechrevo.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class OpenHelper extends SQLiteOpenHelper {

    public OpenHelper(Context context,String name,int version){
        super(context,name,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table if not exists diary(date varchar(11),cont text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
