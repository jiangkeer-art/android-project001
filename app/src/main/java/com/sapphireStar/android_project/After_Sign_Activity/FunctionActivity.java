package com.sapphireStar.android_project.After_Sign_Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sapphireStar.android_project.R;

import java.util.List;

public class FunctionActivity extends FragmentActivity implements View.OnClickListener{

    //装载Fragment的集合
    private List<Fragment> mFragments;
    //两个Tab对应的按钮布局
    private LinearLayout schedule_btn,mine_btn;
    //两个Tab对应的ImageButton
    private ImageButton schedule_ibt,mine_ibt;
    //两个Tab对应的Fragment
    private Fragment schedule_tab = new Fragment();
    private Fragment mine_tab = new Fragment();

    FragmentManager fm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        initViews();//初始化控件
        initFragment();//初始化碎片
        selectTab(0);//初始页面为主页面
        initEvents();//初始化事件
    }
    //初始化控件
    private void initViews(){
        schedule_btn = (LinearLayout) findViewById(R.id.schedule_btn);
        mine_btn = (LinearLayout) findViewById(R.id.mine_btn);

        schedule_ibt = (ImageButton) findViewById(R.id.schedule_tab_img);
        mine_ibt = (ImageButton) findViewById(R.id.mine_tab_img);
    }

    private void initEvents(){
        //设置两个Tab的点击事件
        schedule_btn.setOnClickListener(this);
        mine_btn.setOnClickListener(this);
    }

    private void initFragment(){
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.content, schedule_tab);
        transaction.add(R.id.content, mine_tab);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        //先将两个ImageButton置为灰色
        resetImgs();
        switch (view.getId()){
            case R.id.schedule_btn:
                selectTab(0);
                break;
            case R.id.mine_btn:
                selectTab(1);
                break;
        }
    }
    //将两个ImageButton设置为灰色
    private void resetImgs() {
        schedule_ibt.setImageResource(R.drawable.ic_schedule_black);
        mine_ibt.setImageResource(R.drawable.ic_mine_black);
    }

    private void selectTab(int i) {
        //根据点击的Tab设置对应的ImageButton为选中的颜色
        FragmentTransaction transaction = fm.beginTransaction();
        switch (i) {
            case 0:
                schedule_ibt.setImageResource(R.drawable.ic_schedule_selected);
                transaction.replace(R.id.content,schedule_tab);
                break;
            case 1:
                mine_ibt.setImageResource(R.drawable.ic_mine_selected);
                transaction.replace(R.id.content,mine_tab);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}