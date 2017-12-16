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

import static com.example.zhihu.MainActivity.op2;
import static com.example.zhihu.Main_pageAdapter.id1;

public class Main3page extends AppCompatActivity {
    Toolbar toolbar3;
    RecyclerView recycler_view2;
    private List<Main3_page> main3_pageList = new ArrayList<>();
    private Main3_pageAdapter adapter;
    private SwipeRefreshLayout swipe_refresh2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3page);

        sendRequestWithHttpURLConnection();
        toolbar3 = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        recycler_view2 = (RecyclerView) findViewById(R.id.recycler_view2);
        swipe_refresh2 = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh2);
        swipe_refresh2.setColorSchemeResources(R.color.colorbtn);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler_view2.setLayoutManager(layoutManager);
        adapter = new Main3_pageAdapter(main3_pageList);
        swipe_refresh2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_refresh2.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_refresh2.setRefreshing(false);
                        main3_pageList.clear();
                        sendRequestWithHttpURLConnection();
                    }
                }, 1200);
            }
        });
        recycler_view2.setAdapter(adapter);
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://news-at.zhihu.com/api/3/news/hot");
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
            JSONArray jsonArray = jsonObject1.getJSONArray("recent");
            for (int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String M3_title = jsonObject.getString("title");
                String M3_id = jsonObject.getString("news_id");
                String M3_images = jsonObject.getString("thumbnail");
                Main3_page M3 = new Main3_page(M3_title,M3_id,M3_images);
                main3_pageList.add(M3);
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
                RecyclerView recycler_view2 = findViewById(R.id.recycler_view2);
                Main3_pageAdapter adapter = new Main3_pageAdapter(main3_pageList);
                recycler_view2.setAdapter(adapter);
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
                Toast.makeText(Main3page.this,"谢谢您的评价",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
