package com.sapphireStar.entity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.android_project.R;

public class search extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);
        TabHost tab = (TabHost) findViewById(android.R.id.tabhost);
        TabHost tab1 = (TabHost) findViewById(android.R.id.tabhost);

        //初始化TabHost容器
        tab.setup();
        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        tab.addTab(tab.newTabSpec("国内").setIndicator("国内" , null).setContent(R.id.国内));
        tab.addTab(tab.newTabSpec("国际").setIndicator("国际" , null).setContent(R.id.国际));
    }
}
