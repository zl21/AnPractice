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
        //��������
        intent = new Intent();
        //������ת��Ŀ�ĵ�
        intent.setClass(this, cla);
        //����Activity
        startActivity(intent);
    }
}
