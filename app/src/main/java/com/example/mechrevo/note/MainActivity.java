package com.example.mechrevo.note;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button btCreate;
    private List<String> listarray;
    private OpenHelper openHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper=new OpenHelper(this,"note.db",1);
        listView=(ListView) findViewById(R.id.list);
        final UserDao userDao=new UserDao(openHelper);
        //listarray=new ArrayList<>();
        listarray=userDao.query();
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list,R.id.list_text,listarray);
        listView.setAdapter(adapter);
        btCreate=(Button)findViewById(R.id.btCreate);
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,final View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("警告！")
                        .setMessage("确定删除此纪录？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDatabase db=openHelper.getReadableDatabase();
                                UserDao userDao=new UserDao(openHelper);
                                TextView listDate=(TextView)view.findViewById(R.id.list_text);
                                String s=listDate.getText().toString();
                                Log.w("删除的数据",s);
                                Diary diary=new Diary();
                                diary.setDate(s);
                                userDao.delete(diary);
                                listarray.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("否",null)
                        .show();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDao userDao1=new UserDao(openHelper);
                String d=listarray.get(position);
                Log.w("更新的数据",d);
                Diary diary=userDao.queryOne(d);
                Log.w("更新的数据",diary.getDate());
                Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                intent.putExtra("diary",diary);
                startActivity(intent);
            }
        });
    }

}
