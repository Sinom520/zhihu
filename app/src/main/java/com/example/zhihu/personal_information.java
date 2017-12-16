package com.example.zhihu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

import static com.example.zhihu.MainActivity.M_dbHelper;
import static com.example.zhihu.MainActivity.id;
import static com.example.zhihu.MainActivity.op2;

public class personal_information extends AppCompatActivity {
    Toolbar toolbar_p;
    TextView user_name3;
    TextView user_ID3;
    TextView user_br1;
    TextView user_age1;
    TextView user_password3;
    TextView choose1;
    Button insert;
    RadioGroup rG1;
    RadioButton man1;
    RadioButton woman1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        insert = (Button) findViewById(R.id.insert);
        user_name3 = (TextView) findViewById(R.id.user_name3);
        user_ID3 = (TextView) findViewById(R.id.user_ID3);
        user_password3 = (TextView) findViewById(R.id.user_password3);
        user_age1 = (TextView) findViewById(R.id.user_age1);
        user_br1 = (TextView) findViewById(R.id.user_br1);
        choose1 = (TextView) findViewById(R.id.choose1);
        rG1 = (RadioGroup) findViewById(R.id.rG1);
        man1 = (RadioButton) findViewById(R.id.man1);
        woman1 = (RadioButton) findViewById(R.id.woman1);
        toolbar_p = (Toolbar) findViewById(R.id.toolbar_p);

        setSupportActionBar(toolbar_p);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        user_ID3.setText(id);
        SQLiteDatabase db = M_dbHelper.getWritableDatabase();
        Cursor cursor = db.query("User",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                String str1 = cursor.getString(cursor.getColumnIndex("user_ID"));
                if (str1.equals(id)){
                    String str2 = cursor.getString(cursor.getColumnIndex("user_name"));
                    user_name3.setText(str2);
                    String str3 = cursor.getString(cursor.getColumnIndex("user_password"));
                    user_password3.setText(str3);
                    String str4 = cursor.getString(cursor.getColumnIndex("user_sex"));
                    if (str4 == null){
                        choose1.setText("请选择性别");
                    }
                    else {
                        if (str4.equals("m")){
                            man1.setChecked(true);
                        }
                        else {
                            woman1.setChecked(true);
                        }
                    }
                    String str5 = cursor.getString(cursor.getColumnIndex("user_age"));
                    user_age1.setText(str5);
                    String str6 = cursor.getString(cursor.getColumnIndex("user_br"));
                    user_br1.setText(str6);
                    break;
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent op5 = new Intent("ACTION_START5");
                startActivity(op5);
                finish();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(op2);
                finish();
                break;
            case R.id.ese:
                Toast.makeText(personal_information.this,"谢谢您的评价",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
