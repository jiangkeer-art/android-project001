package com.sapphireStar.android_project.After_Sign_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sapphireStar.android_project.MineActivity.Change_Password;
import com.sapphireStar.android_project.R;

public class MineFragment extends Fragment {

    public Button all;
    public ImageButton complete,uncomplete,changed,change_password,change_phone,real_confirm;
    public String phone="";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
        all = view.findViewById(R.id.all);
        complete = view.findViewById(R.id.complete);
        uncomplete = view.findViewById(R.id.incomplete);
        changed = view.findViewById(R.id.changed);
        change_password = view.findViewById(R.id.change_password);
        change_phone = view.findViewById(R.id.change_phone);
        real_confirm = view.findViewById(R.id.identity_confirm);
        OnClick onClick = new OnClick();
        all.setOnClickListener(onClick);
        complete.setOnClickListener(onClick);
        uncomplete.setOnClickListener(onClick);
        changed.setOnClickListener(onClick);
        change_password.setOnClickListener(onClick);
        change_phone.setOnClickListener(onClick);
        real_confirm.setOnClickListener(onClick);
        return view;
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.all:

                case R.id.complete:

                case R.id.incomplete:

                case R.id.changed:

                case R.id.change_password:
                    intent = new Intent(getActivity(), Change_Password.class);
                    phone = getActivity().getIntent().getStringExtra("phone");
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                    break;
                case R.id.change_phone:

                case R.id.identity_confirm:
            }
        }
    }
}
