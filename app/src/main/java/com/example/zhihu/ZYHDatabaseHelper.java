package com.example.zhihu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by hp on 2017/12/13.
 */

public class ZYHDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement, "
            + "user_name text, "
            + "user_ID text, "
            + "user_password text, "
            + "user_images text, "
            + "user_sex text, "
            + "user_age text, "
            + "user_br text)";

    public static final String CREATE_SETUP = "create table Setup ("
            + "id integer primary key autoincrement, "
            + "set_number text, "
            + "set_ID text, "
            + "set_massage text, "
            + "set_images text, "
            + "set_title text)";

    public static final String CREATE_PASSAGE = "create table Passage ("
            + "id integer primary key autoincrement, "
            + "sage_number text, "
            + "sage_ID text, "
            + "sage_images text, "
            + "sage_title text)";

    public static final String CREATE_LOVE_SETUP = "create table Love_setup ("
            + "id integer primary key autoincrement, "
            + "love_s_number text, "
            + "love_s_ID text, "
            + "love_s_massage text, "
            + "love_s_images text, "
            + "love_s_title text)";

    public static final String CREATE_lOVE_PASSAGE = "create table Love_passage ("
            + "id integer primary key autoincrement, "
            + "love_p_number text, "
            + "love_p_ID text, "
            + "love_p_images text, "
            + "love_p_title text)";


    private Context mContext;

    public ZYHDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_SETUP);
        db.execSQL(CREATE_PASSAGE);
        db.execSQL(CREATE_LOVE_SETUP);
        db.execSQL(CREATE_lOVE_PASSAGE);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User") ;
        db.execSQL("drop table if exists Setup");
        db.execSQL("drop table if exists Passage");
        onCreate(db) ;
    }
}
