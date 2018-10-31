package com.example.mechrevo.note;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class Main2Activity extends Activity {
    private Button btSave;
    private Diary diary;
    private EditText date;
    private EditText cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btSave=(Button)findViewById(R.id.btSave);
        date=(EditText)findViewById(R.id.date);
        cont=(EditText)findViewById(R.id.cont);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diary=getDiary();
                UserDao userDao=new UserDao(new OpenHelper(Main2Activity.this,"note.db",1));
                userDao.insert(diary);
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public Diary getDiary(){
        Diary diary1=new Diary();
        diary1.setDate(date.getText().toString());
        diary1.setCont(cont.getText().toString());
        return diary1;
    }

}
