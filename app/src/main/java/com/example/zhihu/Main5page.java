package com.example.zhihu;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import static com.example.zhihu.Main3_pageAdapter.id2;
import static com.example.zhihu.Main3_pageAdapter.intent2;


public class Main5page extends AppCompatActivity {
    Toolbar toolbar5;
    RecyclerView recycler_view5;
    private List<Main5_page> main5_pageList = new ArrayList<>();
    private Main5_pageAdapter adapter;
    private SwipeRefreshLayout swipe_refresh5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5page);

        sendRequestWithHttpURLConnection();
        toolbar5 = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar5);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        recycler_view5 = (RecyclerView) findViewById(R.id.recycler_view5);
        swipe_refresh5 = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh5);
        swipe_refresh5.setColorSchemeResources(R.color.colorbtn);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler_view5.setLayoutManager(layoutManager);
        adapter = new Main5_pageAdapter(main5_pageList);
        swipe_refresh5.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_refresh5.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_refresh5.setRefreshing(false);
                        main5_pageList.clear();
                        sendRequestWithHttpURLConnection();
                    }
                }, 1200);
            }
        });
        recycler_view5.setAdapter(adapter);
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://news-at.zhihu.com/api/4/story/" + id2 + "/long-comments");
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
        try {
            JSONObject jsonObject1 = new JSONObject(response);
            JSONArray jsonArray = jsonObject1.getJSONArray("comments");
            for (int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String M5_author = jsonObject.getString("author");
                String M5_content = jsonObject.getString("content");
                String M5_image = jsonObject.getString("avatar");
                String M5_time = jsonObject.getString("time");
                String M5_likes = jsonObject.getString("likes");
                String M5_id = jsonObject.getString("id");
                Main5_page M5 = new Main5_page(M5_author,M5_likes,M5_time,M5_content,M5_image);
                main5_pageList.add(M5);
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
                RecyclerView recycler_view5 = findViewById(R.id.recycler_view5);
                Main5_pageAdapter adapter = new Main5_pageAdapter(main5_pageList);
                recycler_view5.setAdapter(adapter);
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
                startActivity(intent2);
                finish();
                break;
            case R.id.ese:
                Toast.makeText(Main5page.this,"谢谢您的评价",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
