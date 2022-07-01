package com.sapphireStar.android_project.After_Sign_Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sapphireStar.android_project.BeginActivity.MainActivity;
import com.sapphireStar.android_project.MineActivity.Change_Password;
import com.sapphireStar.android_project.MineActivity.Change_Phone;
import com.sapphireStar.android_project.MineActivity.Identity_Confirm;
import com.sapphireStar.android_project.MineActivity.MyAttention;
import com.sapphireStar.android_project.MineActivity.MyOrderActivity;
import com.sapphireStar.android_project.R;

public class MineFragment extends Fragment {

    public Button exit,attention;
    public ImageButton complete,uncomplete,changed,change_password,change_phone,real_confirm;
    private TextView id,username;
    public String phone="",ID="",adm="",userName="",state="";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
        //添加点击事件监听
        id = view.findViewById(R.id.id);
        attention = view.findViewById(R.id.attention);
        username= view.findViewById(R.id.username);
        exit = view.findViewById(R.id.exit);
        complete = view.findViewById(R.id.complete);
        uncomplete = view.findViewById(R.id.incomplete);
        changed = view.findViewById(R.id.changed);
        change_password = view.findViewById(R.id.change_password);
        change_phone = view.findViewById(R.id.change_phone);
        real_confirm = view.findViewById(R.id.identity_confirm);
        OnClick onClick = new OnClick();
        exit.setOnClickListener(onClick);
        complete.setOnClickListener(onClick);
        uncomplete.setOnClickListener(onClick);
        changed.setOnClickListener(onClick);
        change_password.setOnClickListener(onClick);
        change_phone.setOnClickListener(onClick);
        real_confirm.setOnClickListener(onClick);
        attention.setOnClickListener(onClick);
        //获取从登录界面传来的ID和手机号并显示
        adm = getActivity().getIntent().getStringExtra("adm");
        ID = getActivity().getIntent().getStringExtra("id");
        phone = getActivity().getIntent().getStringExtra("phone");
        userName = "用户名："+phone;
        id.setText(ID);
        username.setText(userName);

        return view;
    }

    //点击事件
    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.complete:
                    //已完成按钮的点击事件
                    state="1";
                    intent = new Intent(getActivity(), MyOrderActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    intent.putExtra("adm",adm);
                    intent.putExtra("state",state);
                    startActivity(intent);
                    break;

                case R.id.incomplete:
                    //未完成按钮的点击事件
                    state="0";
                    //Toast.makeText(getActivity(), "addssdasdasdasda ", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), MyOrderActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    intent.putExtra("adm",adm);
                    intent.putExtra("state",state);
                    startActivity(intent);
                    //Toast.makeText(getActivity(), "addssdasdasdasda ", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.changed:
                    //退改签按钮的点击事件
                    state="2";
                    intent = new Intent(getActivity(), MyOrderActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    intent.putExtra("adm",adm);
                    intent.putExtra("state",state);
                    startActivity(intent);
                    break;

                case R.id.change_password:
                    //修改密码按钮
                    intent = new Intent(getActivity(), Change_Password.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    intent.putExtra("adm",adm);
                    startActivity(intent);
                    break;
                case R.id.change_phone:
                    //修改账号按钮
                    intent = new Intent(getActivity(), Change_Phone.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    intent.putExtra("adm",adm);
                    startActivity(intent);
                    break;

                case R.id.identity_confirm:
                    //添加实名信息按钮
                    intent = new Intent(getActivity(), Identity_Confirm.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    intent.putExtra("adm",adm);
                    startActivity(intent);
                    break;

                case R.id.exit:
                    //注销账号按钮
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("确定要退出当前账号吗？");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    dialog.setNegativeButton("取消",null);
                    dialog.show();
                    break;
                case R.id.attention:
                    //我的收藏按钮
                    intent = new Intent(getActivity(), MyAttention.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    intent.putExtra("adm",adm);
                    startActivity(intent);
                    break;
            }
        }
    }
}
