package com.myapplication01;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myapplication01.base.BaseActivity;
import com.myapplication01.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

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
                    login();

                }
                break;
            case R.id.login_reg_btn:
//                注册
                startIntent(RegistActivity.class);
                break;
        }
    }
//    执行登录
    private void login() {
        final String account = accountEdi.getText().toString().trim();
        final String pwd = pwdEdi.getText().toString().trim();
//        创建线程、
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                子线程
                HashMap<String,Object> map = new HashMap<>();
                map.put("account",account);
                map.put("password",pwd);
                final String result = HttpUtils.doPost("login",map);
//                下面要走主线程，因为UI操作不能子线程实现
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        主线程
                        //toast(result);
//                        将返回的类型数据转化为json对象
                        try {
                            JSONObject jsonObject = new JSONObject(result);
//                            获取code值
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                toast("登录成功！");
//                                跳转主界面
                                startIntent(LookActivity.class);
//                                结束当前activity
                                finish();
                            }
                            else {
                                toast(jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            toast("返回数据不是json格式！");
                        }

                    }
                });
            }
        };
//        启动线程
        new Thread(runnable).start();


    }

}
