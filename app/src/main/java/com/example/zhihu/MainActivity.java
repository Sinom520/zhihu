package com.example.zhihu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static public ZYHDatabaseHelper M_dbHelper;
    static Intent op2 = new Intent("ACTION_START2");
    static String id;//判断用户的唯一标识
    EditText user_name1;
    EditText user_ID1;
    EditText user_password1;
    Button user_enter;
    Button user_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        M_dbHelper = new ZYHDatabaseHelper(this,"Zhihu.db", null, 1);
        M_dbHelper.getWritableDatabase();

        user_enter = (Button) findViewById(R.id.user_enter);
        user_login = (Button) findViewById(R.id.user_login);
        user_name1 = (EditText) findViewById(R.id.user_name1);
        user_ID1 = (EditText) findViewById(R.id.user_ID1);
        user_password1 = (EditText) findViewById(R.id.user_password1);

        user_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1()){
                    if (check2()){
                        if (check3()){
                            if (check4()){
                                Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                startActivity(op2);
                                finish();
                            }
                            else {
                                Toast.makeText(MainActivity.this,"输入密码错误",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this,"输入账号错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this,"用户名错误",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"请输入完整信息",Toast.LENGTH_SHORT).show();
                }
            }
        });
        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent op1 = new Intent("ACTION_START1");
                Toast.makeText(MainActivity.this,"欢迎注册",Toast.LENGTH_SHORT).show();
                startActivity(op1);
                finish();
            }
        });
    }

    private boolean check4() {
        SQLiteDatabase db = M_dbHelper.getWritableDatabase();
        Cursor cursor3 = db.query("User", null, null, null, null, null, null);
        if (cursor3.moveToFirst()) {
            do {
                if (cursor3.getString(cursor3.getColumnIndex("user_password")).equals(user_password1.getText().toString().trim())){
                    id = cursor3.getString(cursor3.getColumnIndex("user_ID"));
                    return true;
                }
            } while (cursor3.moveToNext());
        }
        cursor3.close();
        db.close();
        return false;
    }//判断密码是否正确

    private boolean check3() {
        SQLiteDatabase db = M_dbHelper.getWritableDatabase();
        Cursor cursor2 = db.query("User", null, null, null, null, null, null);
        if (cursor2.moveToFirst()) {
            do {
                if (cursor2.getString(cursor2.getColumnIndex("user_ID")).equals(user_ID1.getText().toString().trim())){
                    return true;
                }
            } while (cursor2.moveToNext());
        }
        cursor2.close();
        db.close();
        return false;
    }//判断账号是否正确

    private boolean check2() {
        SQLiteDatabase db = M_dbHelper.getWritableDatabase();
        Cursor cursor1 = db.query("User", null, null, null, null, null, null);
        if (cursor1.moveToFirst()) {
            do {
                if (cursor1.getString(cursor1.getColumnIndex("user_name")).equals(user_name1.getText().toString().trim())){
                    return true;
                }
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        db.close();
        return false;
    }//判断用户名是否正确

    private boolean check1() {
        if (user_name1.getText().toString().trim().isEmpty()||user_ID1.getText().toString().trim().isEmpty()||user_password1.getText().toString().trim().isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }//判断是否有空缺
}
