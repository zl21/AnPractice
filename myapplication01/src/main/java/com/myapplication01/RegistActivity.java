package com.myapplication01;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.myapplication01.base.BaseActivity;
import com.myapplication01.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by zhoul on 2019/1/2.
 */
public class RegistActivity extends BaseActivity implements View.OnClickListener {
    private Button backBut,regBut,birBut;
    private EditText regAccountEdi,regNameEdi,regPawEdi,regRePwdEdi;
    private RadioGroup rg;
    private RadioButton rBMan,rBWoman;
    private Calendar calendar;
    private String sex = "M";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        init();
        backBut.setOnClickListener(this);
        regBut.setOnClickListener(this);
        birBut.setOnClickListener(this);
        calendar = Calendar.getInstance();
//        ������Button��Ĭ��ֵ
        birBut.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
//        ���õ�ѡ��ļ����¼�
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_man:
                        sex = "M";
                        break;
                    case R.id.rb_woman:
                        sex  = "F";
                        break;
                }
            }

        });
    }
    private void init() {
        backBut = (Button) findViewById(R.id.reg_back_but);
        regBut = (Button) findViewById(R.id.reg_ok_but);
        birBut = (Button) findViewById(R.id.reg_brithday_but);
        regAccountEdi = (EditText) findViewById(R.id.reg_account_edi);
        regNameEdi = (EditText) findViewById(R.id.reg_name_edi);
        regPawEdi = (EditText) findViewById(R.id.reg_password_edi);
        regRePwdEdi = (EditText) findViewById(R.id.reg_rePassword_edi);
        rg = (RadioGroup) findViewById(R.id.reg_rg);
        rBMan = (RadioButton) findViewById(R.id.rb_man);
        rBWoman = (RadioButton) findViewById(R.id.rb_woman);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_back_but:
                break;
            case R.id.reg_ok_but:
                if (TextUtils.isEmpty(regAccountEdi.getText())) {
                    toast("�˺Ų���Ϊ�գ�");
                }
                else if (TextUtils.isEmpty(regPawEdi.getText())) {
                    toast("���벻��Ϊ�գ�");
                }else if (TextUtils.isEmpty(regRePwdEdi.getText())) {
                    toast("���벻һ�£�");
                }
                else {
//                    �ύ
                    reg();

                }
                break;
            case R.id.reg_brithday_but:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birBut.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
//                ��ʾ�Ի���
                datePickerDialog.show();
//                ע���ύ

                break;
        }
    }

    private void reg() {
        Runnable runnablen = new Runnable() {
            @Override
            public void run() {
                HashMap<String,Object> map = new HashMap<>();
                map.put("account",regAccountEdi.getText().toString().trim());
                map.put("name",regNameEdi.getText().toString().trim());
                map.put("sex",sex);
                map.put("birthday",birBut.getText().toString().trim());
                map.put("password", regPawEdi.getText().toString().trim());
                final String result = HttpUtils.doPost("regeist", map);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //toast(result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
//                            ��ȡcodeֵ
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                toast("ע��ɹ���");
//                                ������ǰactivity
                                finish();
                            }
                            else {
                                toast(jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            toast("�������ݲ���json��ʽ��");
                        }

                    }
                });
            }
        };
//        �����߳�
        new Thread(runnablen).start();
    }
}
