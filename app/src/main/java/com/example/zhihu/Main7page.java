package com.example.zhihu;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Handler;

import static com.example.zhihu.Main10_pageAdapter.sign4;
import static com.example.zhihu.Main3_pageAdapter.hide2_image;
import static com.example.zhihu.Main3_pageAdapter.hide2_title;
import static com.example.zhihu.Main3_pageAdapter.id2;
import static com.example.zhihu.Main3_pageAdapter.intent2;
import static com.example.zhihu.Main4_pageAdapter.flag6;
import static com.example.zhihu.Main8_pageAdapter.sign1;
import static com.example.zhihu.MainActivity.M_dbHelper;
import static com.example.zhihu.MainActivity.id;
import static com.example.zhihu.Main_pageAdapter.hide1_images;
import static com.example.zhihu.Main_pageAdapter.hide1_massage;
import static com.example.zhihu.Main_pageAdapter.hide1_title;
import static com.example.zhihu.Main_pageAdapter.id1;
import static com.example.zhihu.Mainpage.op10;
import static com.example.zhihu.Mainpage.op12;
import static com.example.zhihu.Mainpage.op3;
import static com.example.zhihu.Mainpage.op6;

public class Main7page extends AppCompatActivity {
    TextView heat2;
    TextView L_talk1;
    TextView L_talk2;
    TextView S_talk1;
    TextView S_talk2;
    TextView num;
    Toolbar toolbar7;
    private Intent op9 = new Intent("ACTION_START9");
    private Intent op7 = new Intent("ACTION_START7");
    private SwipeRefreshLayout swipe_refresh7;
    private DrawerLayout drawer_Layout7;
    private NavigationView nav_view7;
    private String body;
    private WebView web_view;
    private String Url,Url2;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint({"SetJavaScriptEnabled", "ObsoleteSdkInt"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7page);

        drawer_Layout7 = (DrawerLayout) findViewById(R.id.drawer_layout7);
        nav_view7 = (NavigationView) findViewById(R.id.nav_view7);
        web_view = findViewById(R.id.web_view);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(new WebViewClient());
        web_view.getSettings().setSupportZoom(false);
        web_view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        web_view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        web_view.getSettings().setLoadWithOverviewMode(true);
        web_view.getSettings().setUseWideViewPort(true);
        web_view.getSettings().setDomStorageEnabled(true);
        web_view.getSettings().setAllowFileAccessFromFileURLs(true);
        web_view.getSettings().setAllowUniversalAccessFromFileURLs(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            web_view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            web_view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }

        toolbar7 = findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar7);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.more);
        Url = "http://news-at.zhihu.com/api/2/news/" +id2;
        Url2 = "https://news-at.zhihu.com/api/4/story-extra/"+id2;
        swipe_refresh7 = findViewById(R.id.swipe_refresh7);
        swipe_refresh7.setColorSchemeResources(R.color.colorbtn);
        swipe_refresh7.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_refresh7.setRefreshing(true);
                (new android.os.Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_refresh7.setRefreshing(false);
                        sendRequestWithHttpURLConnection1();
                        sendRequestWithHttpURLConnection2();
                    }
                }, 1200);
            }
        });
        sendRequestWithHttpURLConnection1();
        sendRequestWithHttpURLConnection2();
        View headerView7 = nav_view7.inflateHeaderView(R.layout.head2_layout);
        heat2 = (TextView) headerView7.findViewById(R.id.heat2);
        L_talk1 = (TextView) headerView7.findViewById(R.id.L_talk1);
        L_talk2 = (TextView) headerView7.findViewById(R.id.L_talk2);
        S_talk1 = (TextView) headerView7.findViewById(R.id.S_talk1);
        S_talk2 = (TextView) headerView7.findViewById(R.id.S_talk2);
        num = (TextView) headerView7.findViewById(R.id.num);

        L_talk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main7page.this,"进入长评区",Toast.LENGTH_SHORT).show();
                startActivity(op9);
                finish();
            }
        });
        L_talk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main7page.this,"进入长评区",Toast.LENGTH_SHORT).show();
                startActivity(op9);
                finish();
            }
        });
        S_talk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main7page.this,"进入短评区",Toast.LENGTH_SHORT).show();
                startActivity(op7);
                finish();
            }
        });
        S_talk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main7page.this,"进入短评区",Toast.LENGTH_SHORT).show();
                startActivity(op7);
                finish();
            }
        });

        nav_view7.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.take_hide:
                        if (check1()){
                            SQLiteDatabase db_a = M_dbHelper.getWritableDatabase();
                            ContentValues values_a = new ContentValues();
                            values_a.put("sage_number", id);
                            values_a.put("sage_ID", id2);
                            values_a.put("sage_images",hide2_image);
                            values_a.put("sage_title", hide2_title);
                            db_a.insert("Passage", null, values_a);
                            values_a.clear();
                            Toast.makeText(Main7page.this,"收藏成功",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Main7page.this,"文章已收藏",Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.off_hide:
                        if (check1()){
                            Toast.makeText(Main7page.this,"此文章尚未收藏",Toast.LENGTH_SHORT).show();
                        }
                        else {
                               if (check3()){
                                   SQLiteDatabase db_b = M_dbHelper.getWritableDatabase();
                                   db_b.delete("Passage","sage_ID = ?", new String[]{id2});
                                   Toast.makeText(Main7page.this,"取消成功",Toast.LENGTH_SHORT).show();
                               }
                               else {
                                   SQLiteDatabase db_c = M_dbHelper.getWritableDatabase();
                                   db_c.delete("Passage","sage_ID = ?", new String[]{id2});
                                   Toast.makeText(Main7page.this,"取消成功",Toast.LENGTH_SHORT).show();
                                   sign1 = true;
                                   startActivity(op10);
                                   finish();
                               }
                        }
                        break;

                    case R.id.fond2:
                        if (check2()){
                            SQLiteDatabase db_d = M_dbHelper.getWritableDatabase();
                            ContentValues values_d = new ContentValues();
                            values_d.put("love_p_number", id);
                            values_d.put("love_p_ID", id2);
                            values_d.put("love_p_images",hide2_image);
                            values_d.put("love_p_title", hide2_title);
                            db_d.insert("Love_passage", null, values_d);
                            values_d.clear();
                            Toast.makeText(Main7page.this,"我喜欢",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Main7page.this,"文章已取消",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.off_fond2:
                        if (check2()){
                            Toast.makeText(Main7page.this,"此文章不在我喜欢列表",Toast.LENGTH_SHORT).show();
                        }
                        else {
                           if (check4()){
                               SQLiteDatabase db_e = M_dbHelper.getWritableDatabase();
                               db_e.delete("Love_passage","love_p_ID = ?", new String[]{id2});
                               Toast.makeText(Main7page.this,"取消成功",Toast.LENGTH_SHORT).show();
                           }
                           else {
                               SQLiteDatabase db_f = M_dbHelper.getWritableDatabase();
                               db_f.delete("Love_passage","love_p_ID = ?", new String[]{id2});
                               Toast.makeText(Main7page.this,"取消成功",Toast.LENGTH_SHORT).show();
                               sign4 = true;
                               startActivity(op12);
                               finish();
                           }
                        }
                        break;
                    default:
                }
                return true;
            }
        });
    }

    private boolean check4() {
        if (sign4){
            return true;
        }
        else {
            return false;
        }
    }//判断从哪个页面跳转过来的

    private boolean check3() {
        if (sign1){
            return true;
        }
        else {
            return false;
        }
    }//判断是从哪个页面跳转来的

    private boolean check2() {
        SQLiteDatabase db_g = M_dbHelper.getWritableDatabase();
        Cursor cursor_g = db_g.query("Love_passage",null,null,null,null,null,null);
        if (cursor_g.moveToFirst()){
            do {
                if (cursor_g.getString(cursor_g.getColumnIndex("love_p_ID")).equals(id2)) {
                    return false;
                }
            }while (cursor_g.moveToNext());
        }
        cursor_g.close();
        return true;
    }//判断文章是否已被列为喜爱

    private boolean check1() {
        SQLiteDatabase db_f = M_dbHelper.getWritableDatabase();
        Cursor cursor_f = db_f.query("Passage",null,null,null,null,null,null);
        if (cursor_f.moveToFirst()){
            do {
                if (cursor_f.getString(cursor_f.getColumnIndex("sage_ID")).equals(id2)) {
                    return false;
                }
            }while (cursor_f.moveToNext());
        }
        cursor_f.close();
        return true;
    }//判断文章是否已收藏

    private void sendRequestWithHttpURLConnection2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection1 = null;
                BufferedReader reader1 = null;
                try {
                    URL ur2 = new URL(Url2);
                    connection1 = (HttpURLConnection) ur2.openConnection();
                    connection1.setRequestMethod("GET");
                    connection1.setConnectTimeout(8000);
                    connection1.setReadTimeout(8000);
                    InputStream in1 = connection1.getInputStream();
                    reader1 = new BufferedReader(new InputStreamReader(in1));
                    StringBuilder response1 = new StringBuilder();
                    String line1;
                    while ((line1 = reader1.readLine()) != null) {
                        response1.append(line1);
                    }
                    parseJSONWithJSONObject2(response1.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader1 != null) {
                        try {
                            reader1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection1 != null) {
                        connection1.disconnect();
                    }
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject2(String s) {
        try {
            JSONObject jsonObject7 = new JSONObject(s);
            String s1 = jsonObject7.getString("long_comments");
            String s2 = jsonObject7.getString("popularity");
            String s3 = jsonObject7.getString("short_comments");
            String s4 = jsonObject7.getString("comments");
            heat2.setText(s2);
            L_talk2.setText(s1);
            S_talk2.setText(s3);
            num.setText(s4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    private void sendRequestWithHttpURLConnection1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(Url);
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
                    parseJSONWithJSONObject1(response.toString());
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

    private void parseJSONWithJSONObject1(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            body = jsonObject.getString("body");
            showResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar3, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer_Layout7.openDrawer(GravityCompat.START);
                break;
            case R.id.over:
               if (check3()){
                   startActivity(op3);
                   finish();
               }
               else {
                   sign1 = true;
                   startActivity(op10);
                   finish();
               }
                break;
            default:
        }
        return true;
    }
    private void showResponse() {
        web_view.post(new Runnable() {
            @Override
            public void run() {
                web_view.loadDataWithBaseURL("", getHtmlData(body), "text/html", "UTF-8", "");
            }
        });
    }
}
