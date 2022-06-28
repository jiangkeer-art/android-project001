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
import android.widget.ImageButton;

import com.sapphireStar.android_project.After_Sign_Activity.FunctionActivity;
import com.sapphireStar.android_project.BeginActivity.MainActivity;
import com.sapphireStar.android_project.R;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.dao.impl.NormalUserDaoImpl;
import com.sapphireStar.entity.NormalUser;
import com.sapphireStar.util.CommonDB;

import java.sql.SQLException;

public class Change_Password extends AppCompatActivity {

    public String phone="",ID="";
    public EditText password1,password2,password3;
    public Button confirm;
    public String oldPassword="",newPassword="",reNewPassword="";
    private ImageButton back_to_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        back_to_mine = findViewById(R.id.back_to_mine);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        password3 = findViewById(R.id.password3);
        confirm = findViewById(R.id.confirm);
        phone = getIntent().getStringExtra("phone");
        ID = getIntent().getStringExtra("id");
        OnClick onClick = new OnClick();
        confirm.setOnClickListener(onClick);
        back_to_mine.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {

        @SuppressLint("Range")
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.back_to_mine:
                    intent = new Intent(Change_Password.this, FunctionActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    intent.putExtra("frag",1);
                    startActivity(intent);
                    break;
                case R.id.confirm:
                    oldPassword = password1.getText().toString();
                    newPassword = password2.getText().toString();
                    reNewPassword = password3.getText().toString();
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