package com.example.mechrevo.note;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private SQLiteDatabase db;
    public UserDao(SQLiteOpenHelper openHelper){
        this.db=openHelper.getReadableDatabase();
    }
    public boolean insert(Diary diary){
        ContentValues contentValues=new ContentValues();
        contentValues.put("date",diary.getDate());
        contentValues.put("cont",diary.getCont());
        double insertResult=db.insert("diary",null,contentValues);
        if(insertResult==-1){
            return false;
        }
        return true;
    }
    public boolean delete(Diary diary){
        int deleteResult=db.delete("diary","date=?",new String[]{diary.getDate()});
        if(deleteResult==0){
            return false;
        }
        return true;
    }
    public List<String> query(){
        List<String> list=new ArrayList<>();
        Cursor cursor=db.query("diary",null,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            //Diary diary=new Diary();
            //diary.setDate(cursor.getString(0));
            //diary.setCont(cursor.getString(1));
            list.add(cursor.getString(0));
        }
        return list;
    }
    public Diary queryOne(String date){
        Cursor cursor=db.query("diary",null,"date=?",new String[]{date},null,null,null);

        Diary diary=new Diary();
        while (cursor.moveToNext()){
            diary.setDate(cursor.getString(cursor.getColumnIndex("date")));
            Log.w("查询的date",cursor.getString(cursor.getColumnIndex("date")));
            diary.setCont(cursor.getString(cursor.getColumnIndex("cont")));
            Log.w("查询的cont",cursor.getString(cursor.getColumnIndex("cont")));
        }
        return diary;
    }
    public int  updata(ContentValues contentValues,String date){
        return  db.update("diary",contentValues,"date=?",new String[]{date});
    }
}
