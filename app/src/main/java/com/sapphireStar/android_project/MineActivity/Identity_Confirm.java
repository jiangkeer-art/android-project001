package com.sapphireStar.android_project.MineActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.sapphireStar.android_project.After_Sign_Activity.FunctionActivity;
import com.sapphireStar.android_project.BeginActivity.MainActivity;
import com.sapphireStar.android_project.R;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.dao.impl.NormalUserDaoImpl;

import java.sql.SQLException;

public class Identity_Confirm extends AppCompatActivity {

    public String phone="",ID="",adm="";
    public EditText name,id_number,password;
    public Button confirm;
    public String Name="",idNumber="",Password="";
    private ImageButton back_to_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_identity);
        back_to_mine = findViewById(R.id.back_to_mine);
        name = findViewById(R.id.name);
        id_number = findViewById(R.id.id_number);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        phone = getIntent().getStringExtra("phone");
        ID = getIntent().getStringExtra("id");
        adm = getIntent().getStringExtra("adm");
        Identity_Confirm.OnClick onClick = new Identity_Confirm.OnClick();
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
                    intent = new Intent(Identity_Confirm.this, FunctionActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    intent.putExtra("adm",adm);
                    intent.putExtra("frag",1);
                    startActivity(intent);
                    break;
                case R.id.confirm:
                    Name = name.getText().toString();
                    idNumber = id_number.getText().toString();
                    Password = password.getText().toString();
                    NormalUserDao normalUserDao = new NormalUserDaoImpl();
                    try {
                        normalUserDao.modifyIdNumber(phone,Password,idNumber,Name);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    intent = new Intent(Identity_Confirm.this, FunctionActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    intent.putExtra("adm",adm);
                    intent.putExtra("frag",1);
                    startActivity(intent);
                    break;
            }
        }
    }
}
