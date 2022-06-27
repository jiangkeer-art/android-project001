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

import com.sapphireStar.android_project.BeginActivity.MainActivity;
import com.sapphireStar.android_project.R;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.dao.impl.NormalUserDaoImpl;
import com.sapphireStar.entity.NormalUser;
import com.sapphireStar.util.CommonDB;

import java.sql.SQLException;

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
                    NormalUserDao normalUserDao = new NormalUserDaoImpl(sqlite);
                    try {
                        normalUserDao.modifyPassword(phone,newPassword,reNewPassword,oldPassword);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    intent = new Intent(Change_Password.this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}