package com.example.zhihu;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
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

public class Mainpage extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recycler_view;
    TextView person_name;
    ImageView head_image;
    static public Intent op4 = new Intent("ACTION_START4");
    static public Intent op6 = new Intent("ACTION_START6");
    static public Intent op3 = new Intent("ACTION_START3");
    static public  Intent op10 = new Intent("ACTION_START10");
    static public Intent op11 = new Intent("ACTION9");
    static public Intent op12 = new Intent("ACTION10");
    private DrawerLayout drawer_Layout;
    private NavigationView nav_view;
    private List<Main_page> main_pageList = new ArrayList<>();
    private Main_pageAdapter adapter;
    private SwipeRefreshLayout swipe_refresh;
    private PopupWindow window;
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        sendRequestWithHttpURLConnection();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer_Layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipe_refresh.setColorSchemeResources(R.color.colorbtn);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler_view.setLayoutManager(layoutManager);
        adapter = new Main_pageAdapter(main_pageList);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_refresh.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_refresh.setRefreshing(false);
                        main_pageList.clear();
                        sendRequestWithHttpURLConnection();
                    }
                }, 1200);
            }
        });
        recycler_view.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }
        View headerView = nav_view.inflateHeaderView(R.layout.head1_layout);
        person_name = (TextView) headerView.findViewById(R.id.person_name);
        head_image = (ImageView) headerView.findViewById(R.id.head_image);
        SQLiteDatabase db_1 = M_dbHelper.getWritableDatabase();
        Cursor cursor_1 = db_1.query("User", null, null, null, null, null, null);
        if (cursor_1.moveToFirst()) {
            do {
                if (cursor_1.getString(cursor_1.getColumnIndex("user_ID")).equals(id)) {
                    String string = cursor_1.getString(cursor_1.getColumnIndex("user_name"));
                    String string1 = cursor_1.getString(cursor_1.getColumnIndex("user_images"));
                    person_name.setText(string);
                    Glide.with(this).load(string1).asBitmap().placeholder(R.drawable.picture).error(R.drawable.picture).into(head_image);
                }
            } while (cursor_1.moveToNext());
        }
        cursor_1.close();
        db_1.close();
        head_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow();
            }
        });
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.person_ifm:
                        startActivity(op4);
                        Toast.makeText(Mainpage.this, "enter personal_information page", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case R.id.history:
                        Toast.makeText(Mainpage.this, "No footprints", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.article:
                        startActivity(op10);
                        finish();
                        break;
                    case R.id.setup:
                        startActivity(op6);
                        finish();
                        break;
                    case R.id.L_setup:
                        startActivity(op11);
                        finish();
                        break;
                    case R.id.L_article:
                        startActivity(op12);
                        finish();
                        break;
                    case R.id.night:
                        Toast.makeText(Mainpage.this, "turn to night", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.delete:
                        Snackbar.make(nav_view, "确定退出当前账号", Snackbar.LENGTH_LONG)
                                .setAction("是", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent op8 = new Intent("ACTION_START8");
                                        startActivity(op8);
                                        Toast.makeText(Mainpage.this, "您已退出", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                })
                                .show();
                        break;
                    default:
                }
                return true;
            }
        });
    }

    private void showPopwindow() {
        View contentView = LayoutInflater.from(Mainpage.this).inflate(R.layout.popwindow, null);
        window = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setContentView(contentView);
        //设置各个控件的点击响应
        TextView take = (TextView) contentView.findViewById(R.id.take);
        TextView from_1 = (TextView) contentView.findViewById(R.id.from);
        TextView abolish = (TextView) contentView.findViewById(R.id.abolish);
        //显示PopupWindow
        View rootview = LayoutInflater.from(Mainpage.this).inflate(R.layout.main_page, null);
        window.setFocusable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Mainpage.this,"拍照",Toast.LENGTH_SHORT).show();
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT < 24) {
                    imageUri = Uri.fromFile(outputImage);
                } else {
                    imageUri = FileProvider.getUriForFile(Mainpage.this, "com.example", outputImage);
                }
                // 启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                window.dismiss();
            }
        });
        from_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Mainpage.this,"从相册选择照片",Toast.LENGTH_SHORT).show();
                if (ContextCompat.checkSelfPermission(Mainpage.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Mainpage.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
                } else {
                    openAlbum();
                }
                window.dismiss();
            }
        });
        abolish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://news-at.zhihu.com/api/3/sections");
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
            JSONArray jsonArray = jsonObject1.getJSONArray("data");
            for (int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String description = jsonObject.getString("description");
                String main_id = jsonObject.getString("id");
                String main_name = jsonObject.getString("name");
                String images = jsonObject.getString("thumbnail");
                Main_page M = new Main_page(main_name,description,main_id,images);
                main_pageList.add(M);
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
                RecyclerView recycler_view = findViewById(R.id.recycler_view);
                Main_pageAdapter adapter = new Main_pageAdapter(main_pageList);
                recycler_view.setAdapter(adapter);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                drawer_Layout.openDrawer(GravityCompat.START);
                break;
            case R.id.set:
                Toast.makeText(Mainpage.this,"进行设置操作",Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(Mainpage.this,"最新版本",Toast.LENGTH_SHORT).show();
                break;
            case R.id.look:
                startActivity(op2);
                finish();
                break;
            case R.id.hot:
                startActivity(op3);
                finish();
                break;
            default:
        }
        return true;
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        SQLiteDatabase db_x = M_dbHelper.getWritableDatabase();
                        ContentValues values_x = new ContentValues();
                        values_x.put("user_images", String.valueOf(imageUri));
                        db_x.update("User",values_x,"user_ID = ?",new String[]{id});
                        head_image.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id_1 = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id_1;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            SQLiteDatabase db_y = M_dbHelper.getWritableDatabase();
            ContentValues values_y = new ContentValues();
            values_y.put("user_images",imagePath);
            db_y.update("User",values_y,"user_ID = ?",new String[]{id});
            head_image.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
}
