package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        EditText editText1=(EditText) findViewById(R.id.account1);
        EditText editText2=(EditText) findViewById(R.id.password1);
        EditText editText3=(EditText) findViewById(R.id.password2);
        EditText editText4=(EditText) findViewById(R.id.nickname);
        EditText editText5=(EditText) findViewById(R.id.name);
        EditText editText6=(EditText) findViewById(R.id.identify);
        Button button=(Button) findViewById(R.id.confirm);
        ImageButton imageButton=(ImageButton) findViewById(R.id.login);
        CheckBox checkBox=(CheckBox) findViewById(R.id.checkbox);
        AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(register.this,MainActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText1.getText().length()==11){

                    if((editText2.getText().toString()).equals(editText3.getText().toString())){
                        if((editText2.getText().length()==0)||(editText3.getText().length()==0)){
                            builder.setTitle("通知")
                                    .setMessage("密码不能为空")
                                    .setPositiveButton("确定", null)
                                    .create()
                                    .show();
                        }else{
                            if(editText4.getText().length()==0){
                                builder.setTitle("通知")
                                        .setMessage("昵称不能为空")
                                        .setPositiveButton("确定", null)
                                        .create()
                                        .show();
                            }else{
                                if(editText5.getText().length()==0){
                                    builder.setTitle("通知")
                                            .setMessage("姓名不能为空")
                                            .setPositiveButton("确定", null)
                                            .create()
                                            .show();
                                }else{
                                    if(editText6.getText().length()==18){
                                        if(checkBox.isChecked()==true){
                                            Intent intent=new Intent();
                                            intent.setClass(register.this,MainActivity.class);
                                            startActivity(intent);
                                        }else{
                                            builder.setTitle("通知")
                                                    .setMessage("请勾选协议")
                                                    .setPositiveButton("确定", null)
                                                    .create()
                                                    .show();
                                        }
                                    }else{
                                        builder.setTitle("通知")
                                                .setMessage("身份证格式不正确")
                                                .setPositiveButton("确定", null)
                                                .create()
                                                .show();
                                    }
                                }
                            }
                        }
                    }else{
                        builder.setTitle("通知")
                                .setMessage("两次密码输入不一致")
                                .setPositiveButton("确定", null)
                                .create()
                                .show();
                    }
                }else{
                    builder.setTitle("通知")
                            .setMessage("手机号格式不正确")
                            .setPositiveButton("确定", null)
                            .create()
                            .show();
                }
            }
        });
    }
}
