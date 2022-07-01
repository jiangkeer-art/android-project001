package com.sapphireStar.android_project.MineActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sapphireStar.android_project.After_Sign_Activity.FunctionActivity;
import com.sapphireStar.android_project.BeginActivity.MainActivity;
import com.sapphireStar.android_project.R;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.dao.impl.NormalUserDaoImpl;

import java.sql.SQLException;

public class Change_Phone extends AppCompatActivity {

    public String phone="",ID="";
    public EditText new_phone,password;
    public Button confirm;
    public String newPhone="",Password="";
    private ImageButton back_to_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        back_to_mine = findViewById(R.id.back_to_mine);
        new_phone = findViewById(R.id.new_phone);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        phone = getIntent().getStringExtra("phone");
        ID = getIntent().getStringExtra("id");
        Change_Phone.OnClick onClick = new Change_Phone.OnClick();
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
                    intent = new Intent(Change_Phone.this, FunctionActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    intent.putExtra("frag",1);
                    startActivity(intent);
                    break;
                case R.id.confirm:
                    newPhone = new_phone.getText().toString();
                    Password = password.getText().toString();
                    NormalUserDao normalUserDao = new NormalUserDaoImpl();
                    try {
                        normalUserDao.modifyPhone(phone,newPhone,Password);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    intent = new Intent(Change_Phone.this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
