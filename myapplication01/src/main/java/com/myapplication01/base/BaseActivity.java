package com.myapplication01.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by zhoul on 2019/1/2.
 */
public class BaseActivity extends Activity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void toast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    protected void startIntent(Class cla) {
        //创建对象
        intent = new Intent();
        //设置跳转的目的地
        intent.setClass(this, cla);
        //启动Activity
        startActivity(intent);
    }
}
