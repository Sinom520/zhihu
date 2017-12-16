package com.example.zhihu;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.zhihu.MainActivity.M_dbHelper;
import static com.example.zhihu.MainActivity.id;
import static com.example.zhihu.MainActivity.op2;

public class Main10page extends AppCompatActivity {
    Toolbar toolbar10;
    RecyclerView recycler_view10;
    private List<Main10_page> main10_pageList = new ArrayList<>();
    private Main10_pageAdapter adapter;
    private Boolean sign5 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10page);

        toolbar10 = (Toolbar) findViewById(R.id.toolbar10);
        setSupportActionBar(toolbar10);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        recycler_view10 = (RecyclerView) findViewById(R.id.recycler_view10);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler_view10.setLayoutManager(layoutManager);
        adapter = new Main10_pageAdapter(main10_pageList);
        recycler_view10.setAdapter(adapter);

        SQLiteDatabase db3 = M_dbHelper.getReadableDatabase();
        Cursor cursor1 = db3.query("Love_passage", null, null, null, null, null, null);
        if (cursor1.moveToFirst()) {
            do {
                if (cursor1.getString(cursor1.getColumnIndex("love_p_number")).equals(id)) {
                    String str1 = cursor1.getString(cursor1.getColumnIndex("love_p_title"));
                    String str2 = cursor1.getString(cursor1.getColumnIndex("love_p_ID"));
                    String str4 = cursor1.getString(cursor1.getColumnIndex("love_p_images"));
                    Main10_page M10 = new Main10_page(str1,str2,str4);
                    main10_pageList.add(M10);
                    showResponse();
                    sign5 = false;
                }
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        if (sign5){
            Toast.makeText(Main10page.this,"暂无喜爱文章",Toast.LENGTH_SHORT).show();
        }
    }

    private void showResponse() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recycler_view10 = findViewById(R.id.recycler_view10);
                Main10_pageAdapter adapter = new Main10_pageAdapter(main10_pageList);
                recycler_view10.setAdapter(adapter);
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
                Toast.makeText(Main10page.this,"谢谢您的评价",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
