package com.example.zhihu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.zhihu.MainActivity.M_dbHelper;
import static com.example.zhihu.MainActivity.id;
import static com.example.zhihu.MainActivity.op2;
import static com.example.zhihu.Main_pageAdapter.hide1_title;
import static com.example.zhihu.Main_pageAdapter.id1;

public class Main4page extends AppCompatActivity {
    Toolbar toolbar4;
    RecyclerView recycler_view4;
    private List<Main4_page> main4_pageList = new ArrayList<>();
    private Main4_pageAdapter adapter;
    private Boolean flag3 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4page);

        toolbar4 = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar4);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        recycler_view4 = (RecyclerView) findViewById(R.id.recycler_view4);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler_view4.setLayoutManager(layoutManager);
        adapter = new Main4_pageAdapter(main4_pageList);
        recycler_view4.setAdapter(adapter);

        SQLiteDatabase db1 = M_dbHelper.getReadableDatabase();
        Cursor cursor1 = db1.query("Setup", null, null, null, null, null, null);
        if (cursor1.moveToFirst()) {
            do {
                if (cursor1.getString(cursor1.getColumnIndex("set_number")).equals(id)) {
                    String str1 = cursor1.getString(cursor1.getColumnIndex("set_title"));
                    String str2 = cursor1.getString(cursor1.getColumnIndex("set_ID"));
                    String str3 = cursor1.getString(cursor1.getColumnIndex("set_massage"));
                    String str4 = cursor1.getString(cursor1.getColumnIndex("set_images"));
                    Main4_page M4 = new Main4_page(str1,str2,str3,str4);
                    main4_pageList.add(M4);
                    showResponse();
                    flag3 = false;
                }
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        if (flag3){
            Toast.makeText(Main4page.this,"暂无收藏",Toast.LENGTH_SHORT).show();
        }
}

    private void showResponse() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recycler_view4 = findViewById(R.id.recycler_view4);
                Main4_pageAdapter adapter = new Main4_pageAdapter(main4_pageList);
                recycler_view4.setAdapter(adapter);
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
                Toast.makeText(Main4page.this,"谢谢您的评价",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
