package com.example.mechrevo.note;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
    private Button btSave;
    private Diary diary;
    private EditText date;
    private EditText cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btSave=(Button)findViewById(R.id.btSave2);
        date=(EditText)findViewById(R.id.date2);
        cont=(EditText)findViewById(R.id.cont2);
        Intent intent=getIntent();
        Diary diary1=(Diary)intent.getSerializableExtra("diary");
        date.setText(diary1.getDate());
        final String s=diary1.getDate();
        cont.setText(diary1.getCont());
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues=new ContentValues();
                contentValues.put("date",date.getText().toString());
                contentValues.put("cont",cont.getText().toString());
                UserDao userDao=new UserDao(new OpenHelper(Main3Activity.this,"note.db",1));
                userDao.updata(contentValues,s);
                Intent intent=new Intent(Main3Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
