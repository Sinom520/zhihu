package com.example.zhihu;

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

public class Main6page extends AppCompatActivity {
    Toolbar toolbar6;
    RecyclerView recycler_view6;
    private List<Main6_page> main6_pageList = new ArrayList<>();
    private Main6_pageAdapter adapter;
    private SwipeRefreshLayout swipe_refresh6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6page);

        sendRequestWithHttpURLConnection();
        toolbar6 = (Toolbar) findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar6);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        recycler_view6 = (RecyclerView) findViewById(R.id.recycler_view6);
        swipe_refresh6 = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh6);
        swipe_refresh6.setColorSchemeResources(R.color.colorbtn);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler_view6.setLayoutManager(layoutManager);
        adapter = new Main6_pageAdapter(main6_pageList);
        swipe_refresh6.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_refresh6.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_refresh6.setRefreshing(false);
                        main6_pageList.clear();
                        sendRequestWithHttpURLConnection();
                    }
                }, 1200);
            }
        });
        recycler_view6.setAdapter(adapter);
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://news-at.zhihu.com/api/4/story/" + id2 + "/short-comments");
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
                String M6_author = jsonObject.getString("author");
                String M6_content = jsonObject.getString("content");
                String M6_image = jsonObject.getString("avatar");
                String M6_time = jsonObject.getString("time");
                String M6_likes = jsonObject.getString("likes");
                String M6_id = jsonObject.getString("id");
                Main6_page M6 = new Main6_page(M6_author,M6_likes,M6_time,M6_content,M6_image);
                main6_pageList.add(M6);
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
                RecyclerView recycler_view6 = findViewById(R.id.recycler_view6);
                Main6_pageAdapter adapter = new Main6_pageAdapter(main6_pageList);
                recycler_view6.setAdapter(adapter);
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
                Toast.makeText(Main6page.this,"谢谢您的评价",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
