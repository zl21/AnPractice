package com.myapplication01;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.myapplication01.base.BaseActivity;

import java.util.Calendar;

/**
 * Created by zhoul on 2019/1/2.
 */
public class RegistActivity extends BaseActivity implements View.OnClickListener {
    private Button backBut,regBut,birBut;
    private EditText regAccountEdi,regNameEdi,regPawEdi,regRePwdEdi;
    private RadioGroup rg;
    private RadioButton rBMan,rBWoman;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        init();
        backBut.setOnClickListener(this);
        regBut.setOnClickListener(this);
        birBut.setOnClickListener(this);
        calendar = Calendar.getInstance();
//        给生日Button赋默认值
        birBut.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
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
        rBWoman = (RadioButton) findViewById(R.id.reg_woman);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_back_but:
                break;
            case R.id.reg_ok_but:
                break;
            case R.id.reg_brithday_but:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birBut.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
//                显示对话框
                datePickerDialog.show();
                break;
        }
    }
}
