package com.myapplication01;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myapplication01.base.BaseActivity;

/**
 * Created by zhoul on 2019/1/2.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
//    成员变量
    private Button loginBut,regBut;
    private EditText accountEdi,pwdEdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
//        按钮监听事件
//        this是当前类，super是当前类的父类
        loginBut.setOnClickListener(this);
        regBut.setOnClickListener(this);
    }

    private void init() {
        loginBut = (Button) findViewById(R.id.login_login_but);
        regBut = (Button) findViewById(R.id.login_reg_btn);
        accountEdi = (EditText) findViewById(R.id.login_account_edi);
        pwdEdi = (EditText) findViewById(R.id.login_password_edi);
    }

    @Override
//    view是所有button、editView等的父
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_login_but:
//                登录
                if(TextUtils.isEmpty(accountEdi.getText())) {
                    toast("账号不能为空！");
                    return;
                }
                else if (!accountEdi.getText().toString().trim().matches("[0-9]*")) {
                    toast("账号只能为数字！");
                    return;
                    }
                else if (TextUtils.isEmpty(pwdEdi.getText())) {
                    toast("密码不能为空！");
                    return;
                }
                else {
//            网络请求

                }
                break;
            case R.id.login_reg_btn:
//                注册
                break;
        }
    }
}
