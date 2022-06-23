package com.sapphireStar.android_project.After_Sign_Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sapphireStar.android_project.R;


public class SearchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        TabHost tab = view.findViewById(android.R.id.tabhost);
        TabHost tab1 = view.findViewById(android.R.id.tabhost);

        //初始化TabHost容器
        tab.setup();
        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        tab.addTab(tab.newTabSpec("国内").setIndicator("国内" , null).setContent(R.id.国内));
        tab1.addTab(tab.newTabSpec("国际").setIndicator("国际" , null).setContent(R.id.国际));

        return view;
    }
}