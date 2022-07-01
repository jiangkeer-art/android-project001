package com.sapphireStar.android_project.BeginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sapphireStar.android_project.R;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.dao.impl.NormalUserDaoImpl;

import java.sql.SQLException;

public class ForgetPassword extends AppCompatActivity {

    public EditText phone_number,password2,password3;
    public Button confirm;
    public String phone="",newPassword="",reNewPassword="";
    private ImageButton back_to_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加点击事件监听
        setContentView(R.layout.activity_forget_password);
        back_to_mine = findViewById(R.id.back_to_mine);
        phone_number = findViewById(R.id.phone);
        password2 = findViewById(R.id.password2);
        password3 = findViewById(R.id.password3);
        confirm = findViewById(R.id.confirm);
        ForgetPassword.OnClick onClick = new ForgetPassword.OnClick();
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
                    //返回按钮
                    intent = new Intent(ForgetPassword.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.confirm:
                    //修改密码
                    phone = phone_number.getText().toString();
                    newPassword = password2.getText().toString();
                    reNewPassword = password3.getText().toString();
                    NormalUserDao normalUserDao = new NormalUserDaoImpl();
                    try {
                        normalUserDao.forgetPassword(phone,newPassword,reNewPassword);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    intent = new Intent(ForgetPassword.this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}