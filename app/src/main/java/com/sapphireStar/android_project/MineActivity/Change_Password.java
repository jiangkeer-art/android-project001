package com.sapphireStar.android_project.MineActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sapphireStar.android_project.After_Sign_Activity.FunctionActivity;
import com.sapphireStar.android_project.After_Sign_Activity.MineFragment;
import com.sapphireStar.android_project.BeginActivity.MainActivity;
import com.sapphireStar.android_project.DataBase.DataBaseHelper;
import com.sapphireStar.android_project.R;
import com.sapphireStar.entity.CommonDB;

public class Change_Password extends AppCompatActivity {

    public String phone="";
    public EditText password1,password2,password3;
    public Button confirm;
    public String oldPassword="",newPassword="",reNewPassword="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        password3 = findViewById(R.id.password3);
        confirm = findViewById(R.id.confirm);
        OnClick onClick = new OnClick();
        confirm.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {

        @SuppressLint("Range")
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.confirm:
                    oldPassword = password1.getText().toString();
                    newPassword = password2.getText().toString();
                    reNewPassword = password3.getText().toString();
                    phone = getIntent().getStringExtra("phone");
                    CommonDB db = new CommonDB();
                    SQLiteDatabase sqlite = db.getSqliteObject(Change_Password.this,"FlightDataBase.db");
                    Cursor cursor = sqlite.query("User",null,null,null,null,null,null);
                    if(cursor.moveToFirst()){
                        do{
                            if(phone.equals(cursor.getString(cursor.getColumnIndex("phone")))){
                                if(oldPassword.equals(cursor.getString(cursor.getColumnIndex("password")))){
                                    if(newPassword.equals(reNewPassword)){
                                        ContentValues values = new ContentValues();
                                        values.put("password",newPassword);
                                        sqlite.update("User",values,"phone="+phone,null);
                                    }
                                }
                            }
                        }while(cursor.moveToNext());
                    }
                    cursor.close();
                    intent = new Intent(Change_Password.this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}