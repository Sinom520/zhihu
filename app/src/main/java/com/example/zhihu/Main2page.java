package com.example.zhihu;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.zhihu.Main4_pageAdapter.flag6;
import static com.example.zhihu.Main9_pageAdapter.sign2;
import static com.example.zhihu.MainActivity.M_dbHelper;
import static com.example.zhihu.MainActivity.id;
import static com.example.zhihu.MainActivity.op2;
import static com.example.zhihu.Main_pageAdapter.hide1_images;
import static com.example.zhihu.Main_pageAdapter.hide1_massage;
import static com.example.zhihu.Main_pageAdapter.hide1_title;
import static com.example.zhihu.Main_pageAdapter.id1;
import static com.example.zhihu.Mainpage.op11;
import static com.example.zhihu.Mainpage.op6;

public class Main2page extends AppCompatActivity {
    Toolbar toolbar2;
    RecyclerView recycler_view1;
    private List<Main2_page> main2_pageList = new ArrayList<>();
    private Main2_pageAdapter adapter;
    private SwipeRefreshLayout swipe_refresh1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2page);
        sendRequestWithHttpURLConnection();
        toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        recycler_view1 = (RecyclerView) findViewById(R.id.recycler_view1);
        swipe_refresh1 = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh1);
        swipe_refresh1.setColorSchemeResources(R.color.colorbtn);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler_view1.setLayoutManager(layoutManager);
        adapter = new Main2_pageAdapter(main2_pageList);
        swipe_refresh1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_refresh1.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_refresh1.setRefreshing(false);
                        main2_pageList.clear();
                        sendRequestWithHttpURLConnection();
                    }
                }, 1200);
            }
        });
        recycler_view1.setAdapter(adapter);
    }

    private boolean check1() {
        SQLiteDatabase db_h = M_dbHelper.getWritableDatabase();
        Cursor cursor_h = db_h.query("Setup",null,null,null,null,null,null);
        if (cursor_h.moveToFirst()){
            do {
                if (cursor_h.getString(cursor_h.getColumnIndex("set_ID")).equals(id1)) {
                    return false;
                }
            }while (cursor_h.moveToNext());
        }
        cursor_h.close();
        return true;
    }//判断专栏是否已收藏

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://news-at.zhihu.com/api/3/section/"+id1);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    parseJSONWithJSONObject(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String response) {
        String image = null;
        try {
            JSONObject jsonObject1 = new JSONObject(response);
            String timestamp=jsonObject1.getString("timestamp");
            JSONArray jsonArray = jsonObject1.getJSONArray("stories");
            for (int i = 0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONArray jsonArray1=jsonObject.getJSONArray("images");
                for (int j=0;j<jsonArray1.length();j++) {
                    image=jsonArray1.getString(j);
                }
                String M2_date = jsonObject.getString("date");
                String M2_display_date = jsonObject.getString("display_date");
                String M2_id = jsonObject.getString("id");
                String M2_title = jsonObject.getString("title");
                Main2_page M2 = new Main2_page(M2_title,M2_display_date,M2_id,M2_date,image);
                main2_pageList.add(M2);
                showResponse();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showResponse() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recycler_view1 = findViewById(R.id.recycler_view1);
                Main2_pageAdapter adapter = new Main2_pageAdapter(main2_pageList);
                recycler_view1.setAdapter(adapter);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar4,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.hide:
                if (check1()){
                    SQLiteDatabase db_i = M_dbHelper.getWritableDatabase();
                    ContentValues values_i = new ContentValues();
                    values_i.put("set_number", id);
                    values_i.put("set_ID", id1);
                    values_i.put("set_images",hide1_images);
                    values_i.put("set_massage",hide1_massage);
                    values_i.put("set_title", hide1_title);
                    db_i.insert("Setup", null, values_i);
                    values_i.clear();
                    Toast.makeText(Main2page.this,"收藏成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Main2page.this,"专栏已收藏",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.off:
                if (check1()){
                    Toast.makeText(Main2page.this,"此专栏尚未收藏",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (check2()){
                        SQLiteDatabase db_j = M_dbHelper.getWritableDatabase();
                        db_j.delete("Setup","set_ID = ?", new String[]{id1});
                        Toast.makeText(Main2page.this,"取消成功",Toast.LENGTH_SHORT).show();
                        flag6 = false;
                        startActivity(op6);
                        finish();
                    }
                    else {
                        SQLiteDatabase db_k = M_dbHelper.getWritableDatabase();
                        db_k.delete("Setup","set_ID = ?", new String[]{id1});
                        Toast.makeText(Main2page.this,"取消成功",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.fond1:
                if (check3()){
                    SQLiteDatabase db_r = M_dbHelper.getWritableDatabase();
                    ContentValues values_r = new ContentValues();
                    values_r.put("love_s_number", id);
                    values_r.put("love_s_ID", id1);
                    values_r.put("love_s_images",hide1_images);
                    values_r.put("love_s_massage",hide1_massage);
                    values_r.put("love_s_title", hide1_title);
                    db_r.insert("Love_setup", null, values_r);
                    values_r.clear();
                    Toast.makeText(Main2page.this,"我喜欢",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Main2page.this,"专栏已在喜欢列表",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.off_fond1:
                if (check3()){
                    Toast.makeText(Main2page.this,"此专栏还未列入我喜欢列表",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (check4()){
                        SQLiteDatabase db_l = M_dbHelper.getWritableDatabase();
                        db_l.delete("Love_setup","love_s_ID = ?", new String[]{id1});
                        Toast.makeText(Main2page.this,"取消成功",Toast.LENGTH_SHORT).show();
                        sign2 = false;
                        startActivity(op11);
                        finish();
                    }
                    else {
                        SQLiteDatabase db_m = M_dbHelper.getWritableDatabase();
                        db_m.delete("Love_setup","love_s_ID = ?", new String[]{id1});
                        Toast.makeText(Main2page.this,"取消成功",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
        }
        return true;
    }

    private boolean check4() {
        if (sign2){
            return true;
        }
        else {
            return false;
        }
    }//判断从那个页面进入

    private boolean check3() {
        SQLiteDatabase db_n = M_dbHelper.getWritableDatabase();
        Cursor cursor_n = db_n.query("Love_setup",null,null,null,null,null,null);
        if (cursor_n.moveToFirst()){
            do {
                if (cursor_n.getString(cursor_n.getColumnIndex("love_s_ID")).equals(id1)) {
                    return false;
                }
            }while (cursor_n.moveToNext());
        }
        cursor_n.close();
        return true;
    }//判断专栏是否已在喜爱列表

    private boolean check2() {
        if (flag6){
           return true;
        }
        else {
            return false;
        }
    }
}//判断从那个页面进入的
