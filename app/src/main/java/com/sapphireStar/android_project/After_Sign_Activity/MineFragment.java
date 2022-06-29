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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sapphireStar.android_project.BeginActivity.MainActivity;
import com.sapphireStar.android_project.MineActivity.Change_Password;
import com.sapphireStar.android_project.MineActivity.Change_Phone;
import com.sapphireStar.android_project.MineActivity.Identity_Confirm;
import com.sapphireStar.android_project.OrderActivity.OrderActivity;
import com.sapphireStar.android_project.R;
import com.sapphireStar.android_project.SearchActivity.SearchActivity;

public class MineFragment extends Fragment {

    public Button exit,attention;
    public ImageButton complete,uncomplete,changed,change_password,change_phone,real_confirm;
    private TextView id,username;
    public String phone="",ID="",userName="";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
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

        ID = getActivity().getIntent().getStringExtra("id");
        phone = getActivity().getIntent().getStringExtra("phone");
        userName = "用户名："+phone;
        id.setText(ID);
        username.setText(userName);

        return view;
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.complete:break;

                case R.id.incomplete:break;

                case R.id.changed:break;

                case R.id.change_password:
                    intent = new Intent(getActivity(), Change_Password.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    startActivity(intent);
                    break;
                case R.id.change_phone:
                    intent = new Intent(getActivity(), Change_Phone.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    startActivity(intent);
                    break;

                case R.id.identity_confirm:
                    intent = new Intent(getActivity(), Identity_Confirm.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    startActivity(intent);
                    break;

                case R.id.exit:
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

                    break;
            }
        }
    }
}
