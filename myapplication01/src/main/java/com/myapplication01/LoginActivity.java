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
//    ��Ա����
    private Button loginBut,regBut;
    private EditText accountEdi,pwdEdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
//        ��ť�����¼�
//        this�ǵ�ǰ�࣬super�ǵ�ǰ��ĸ���
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
//    view������button��editView�ȵĸ�
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_login_but:
//                ��¼
                if(TextUtils.isEmpty(accountEdi.getText())) {
                    toast("�˺Ų���Ϊ�գ�");
                    return;
                }
                else if (!accountEdi.getText().toString().trim().matches("[0-9]*")) {
                    toast("�˺�ֻ��Ϊ���֣�");
                    return;
                    }
                else if (TextUtils.isEmpty(pwdEdi.getText())) {
                    toast("���벻��Ϊ�գ�");
                    return;
                }
                else {
//            ��������

                }
                break;
            case R.id.login_reg_btn:
//                ע��
                break;
        }
    }
}
