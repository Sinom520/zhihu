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

public class Main9page extends AppCompatActivity {
    Toolbar toolbar9;
    RecyclerView recycler_view9;
    private List<Main9_page> main9_pageList = new ArrayList<>();
    private Main9_pageAdapter adapter;
    private Boolean sign3 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9page);

        toolbar9 = (Toolbar) findViewById(R.id.toolbar9);
        setSupportActionBar(toolbar9);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        recycler_view9 = (RecyclerView) findViewById(R.id.recycler_view9);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler_view9.setLayoutManager(layoutManager);
        adapter = new Main9_pageAdapter(main9_pageList);
        recycler_view9.setAdapter(adapter);

        SQLiteDatabase db1 = M_dbHelper.getReadableDatabase();
        Cursor cursor1 = db1.query("Love_setup", null, null, null, null, null, null);
        if (cursor1.moveToFirst()) {
            do {
                if (cursor1.getString(cursor1.getColumnIndex("love_s_number")).equals(id)) {
                    String str1 = cursor1.getString(cursor1.getColumnIndex("love_s_title"));
                    String str2 = cursor1.getString(cursor1.getColumnIndex("love_s_ID"));
                    String str3 = cursor1.getString(cursor1.getColumnIndex("love_s_massage"));
                    String str4 = cursor1.getString(cursor1.getColumnIndex("love_s_images"));
                    Main9_page M9 = new Main9_page(str1,str2,str3,str4);
                    main9_pageList.add(M9);
                    showResponse();
                    sign3 = false;
                }
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        if (sign3){
            Toast.makeText(Main9page.this,"暂无收藏",Toast.LENGTH_SHORT).show();
        }
    }

    private void showResponse() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recycler_view9 = findViewById(R.id.recycler_view9);
                Main9_pageAdapter adapter = new Main9_pageAdapter(main9_pageList);
                recycler_view9.setAdapter(adapter);
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
                Toast.makeText(Main9page.this,"谢谢您的评价",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
