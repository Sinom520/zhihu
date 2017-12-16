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

public class Main8page extends AppCompatActivity {
    Toolbar toolbar8;
    RecyclerView recycler_view8;
    private List<Main8_page> main8_pageList = new ArrayList<>();
    private Main8_pageAdapter adapter;
    private Boolean flag11 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8page);

        toolbar8 = (Toolbar) findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar8);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        recycler_view8 = (RecyclerView) findViewById(R.id.recycler_view8);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler_view8.setLayoutManager(layoutManager);
        adapter = new Main8_pageAdapter(main8_pageList);
        recycler_view8.setAdapter(adapter);

        SQLiteDatabase db2 = M_dbHelper.getReadableDatabase();
        Cursor cursor1 = db2.query("Passage", null, null, null, null, null, null);
        if (cursor1.moveToFirst()) {
            do {
                if (cursor1.getString(cursor1.getColumnIndex("sage_number")).equals(id)) {
                    String str1 = cursor1.getString(cursor1.getColumnIndex("sage_title"));
                    String str2 = cursor1.getString(cursor1.getColumnIndex("sage_ID"));
                    String str4 = cursor1.getString(cursor1.getColumnIndex("sage_images"));
                    Main8_page M8 = new Main8_page(str1,str2,str4);
                    main8_pageList.add(M8);
                    showResponse();
                    flag11 = false;
                }
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        if (flag11){
            Toast.makeText(Main8page.this,"暂无收藏",Toast.LENGTH_SHORT).show();
        }
    }

    private void showResponse() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerView recycler_view8 = findViewById(R.id.recycler_view8);
                Main8_pageAdapter adapter = new Main8_pageAdapter(main8_pageList);
                recycler_view8.setAdapter(adapter);
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
                Toast.makeText(Main8page.this,"谢谢您的评价",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
