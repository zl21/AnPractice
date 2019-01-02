package com.myapplication01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.myapplication01.base.BaseActivity;

/**
 * Created by zhoul on 2019/1/2.
 */
public class SpActivity extends BaseActivity {
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_sp);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                startIntent(LoginActivity.class);
                finish();
            }
        }, 2000);
    }
}
