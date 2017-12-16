package com.example.zhihu;

        import android.content.ContentValues;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import static com.example.zhihu.MainActivity.M_dbHelper;
        import static com.example.zhihu.MainActivity.id;
        import static com.example.zhihu.Mainpage.op4;

public class alter extends AppCompatActivity {
    Toolbar toolbar_a;
    EditText user_name4;
    EditText user_password4;
    EditText user_br2;
    EditText user_age2;
    TextView choose2;
    Button insert1;
    RadioGroup rG2;
    RadioButton man2;
    RadioButton woman2;
    private Boolean flag1;
    private Boolean flag2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);

        insert1 = (Button) findViewById(R.id.insert1);
        user_name4 = (EditText) findViewById(R.id.user_name4);
        user_password4 = (EditText) findViewById(R.id.user_password4);
        user_age2 = (EditText) findViewById(R.id.user_age2);
        user_br2 = (EditText) findViewById(R.id.user_br2);
        choose2 = (TextView) findViewById(R.id.choose2);
        rG2 = (RadioGroup) findViewById(R.id.rG2);
        man2 = (RadioButton) findViewById(R.id.man2);
        woman2 = (RadioButton) findViewById(R.id.woman2);
        toolbar_a = (Toolbar) findViewById(R.id.toolbar_a);

        setSupportActionBar(toolbar_a);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        SQLiteDatabase db = M_dbHelper.getWritableDatabase();
        Cursor cursor = db.query("User",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                String str1 = cursor.getString(cursor.getColumnIndex("user_ID"));
                if (str1.equals(id)){
                    String str2 = cursor.getString(cursor.getColumnIndex("user_name"));
                    user_name4.setText(str2);
                    String str3 = cursor.getString(cursor.getColumnIndex("user_password"));
                    user_password4.setText(str3);
                    String str4 = cursor.getString(cursor.getColumnIndex("user_sex"));
                    if (str4 == null){
                        choose2.setText("请选择性别");
                    }
                    else {
                        if (str4.equals("f")){
                            woman2.setChecked(true);
                        }
                        else {
                                man2.setChecked(true);
                            }
                        }
                    String str5 = cursor.getString(cursor.getColumnIndex("user_age"));
                    user_age2.setText(str5);
                    String str6 = cursor.getString(cursor.getColumnIndex("user_br"));
                    user_br2.setText(str6);
                    break;
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        rG2.setOnCheckedChangeListener(rg);
        insert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1()) {
                    if (check2()) {
                        SQLiteDatabase db = M_dbHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("user_name", user_name4.getText().toString().trim());
                        values.put("user_password", user_password4.getText().toString().trim());
                        values.put("user_br",user_br2.getText().toString().trim());
                        values.put("user_age",user_age2.getText().toString().trim());
                        if (check4()){
                            if (check3()){
                                String sex1 = "f";
                                values.put("user_sex",sex1);
                            }
                            else {
                                String sex2 = "m";
                                values.put("user_sex",sex2);
                            }
                        }
                        db.update("User", values, "user_ID = ?", new String[]{id});
                        Toast.makeText(alter.this, "修改成功", Toast.LENGTH_SHORT).show();
                        startActivity(op4);
                        finish();
                    }
                    else {
                        Toast.makeText(alter.this,"密码不符合要求",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(alter.this,"用户名或密码不能空缺",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean check4() {
        if (flag2){
            return true;
        }
        else {
            return false;
        }
    }

    private RadioGroup.OnCheckedChangeListener rg=new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId==man2.getId()){
                flag1 = false;
                flag2 = true;
            }else if(checkedId==woman2.getId()){
                flag1 = true;
                flag2 = true;
            }
        }
    };

    private boolean check3() {
        if (flag1){
            return true;
        }
        else {
            return false;
        }
    }//判断修改后的性别

    private boolean check2() {
        if (user_password4.getText().toString().length() !=8) {
            return false;
        }
        return true;
    }//判断密码是否符合要求

    private boolean check1() {
        if (user_name4.getText().toString().trim().isEmpty()||user_password4.getText().toString().trim().isEmpty()){
            return false;
        }
        return true;
    }//判断密码和用户名是否为空

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.ese:
                Toast.makeText(alter.this,"谢谢您的评价",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
