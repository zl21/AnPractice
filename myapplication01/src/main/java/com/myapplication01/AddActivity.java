package com.myapplication01;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.myapplication01.base.BaseActivity;
import com.myapplication01.utils.AppApplication;
import com.myapplication01.utils.Httputils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by zhoul on 2019/1/4.
 */
public class AddActivity extends BaseActivity implements View.OnClickListener {
    private Button cancel,add,runStart,runEnd;
    private Button exerciseStart,exerciseEnd,walkStart,walkEnd;
    private Button bikeStart,bikeEnd,swimStart,swimEnd;
    private Button ballStart,ballEnd,nightStart,nightEnd;
    private EditText runMileage,bikeMileage,nightMileage;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
        calendar = Calendar.getInstance();
        cancel.setOnClickListener(this);
        add.setOnClickListener(this);
        runStart.setOnClickListener(this);
        runEnd.setOnClickListener(this);
        exerciseStart.setOnClickListener(this);
        exerciseEnd.setOnClickListener(this);
        walkStart.setOnClickListener(this);
        walkEnd.setOnClickListener(this);
        bikeStart.setOnClickListener(this);
        bikeEnd.setOnClickListener(this);
        swimStart.setOnClickListener(this);
        swimEnd.setOnClickListener(this);
        ballStart.setOnClickListener(this);
        ballEnd.setOnClickListener(this);
        nightStart.setOnClickListener(this);
        nightEnd.setOnClickListener(this);
        //runMileage.setOnClickListener(this);
        //bikeMileage.setOnClickListener(this);
        //nightMileage.setOnClickListener(this);
    }

    private void init() {
        cancel = (Button) findViewById(R.id.add_cancel_but);
        add = (Button) findViewById(R.id.add_but);
        runStart = (Button) findViewById(R.id.add_runS_but);
        runEnd = (Button) findViewById(R.id.add_runE_but);
        runMileage = (EditText) findViewById(R.id.add_runM_edi);
        exerciseStart = (Button) findViewById(R.id.add_exerciseS_but);
        exerciseEnd = (Button) findViewById(R.id.add_exerciseE_but);
        walkStart = (Button) findViewById(R.id.add_walkS_but);
        walkEnd = (Button) findViewById(R.id.add_walkE_but);
        bikeStart = (Button) findViewById(R.id.add_bikeS_but);
        bikeEnd = (Button) findViewById(R.id.add_bikeE_but);
        bikeMileage = (EditText) findViewById(R.id.add_bikeM_edi);
        swimStart = (Button) findViewById(R.id.add_swimS_but);
        swimEnd = (Button) findViewById(R.id.add_swimE_but);
        ballStart = (Button) findViewById(R.id.add_ballS_but);
        ballEnd = (Button) findViewById(R.id.add_ballE_but);
        nightStart = (Button) findViewById(R.id.add_nightS_but);
        nightEnd = (Button) findViewById(R.id.add_nightE_but);
        nightMileage = (EditText) findViewById(R.id.add_nightM_edi);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.add_cancel_but:
                finish();
                break;
            case R.id.add_but:
                if (runStart.getText().toString().trim().matches("[\\u4e00-\\u9fa5]*")) {
                    toast("请选择开始时间！");
                    return;
                }
                else if (runEnd.getText().toString().trim().matches("[\\u4e00-\\u9fa5]*")) {
                    toast("请选择结束时间！");
                    return;
                }
                insertDate();
                break;
            default:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        ((Button)v).setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
                    }

                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
        }
    }

    private void insertDate() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                HashMap<String,Object> map = new HashMap<>();
                map.put("account", AppApplication.account);
                map.put("sport_name","晨跑");
                map.put("start_time",runStart.getText().toString().trim());
                map.put("end_time",runEnd.getText().toString().trim());
                map.put("distance",runMileage.getText().toString().trim());
                final String result = Httputils.doPost("insertSport",map);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        toast(result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getInt("code") == 0) {
                                toast("添加成功！");
                            }
                            else {
                                toast(jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            toast("json异常！");
                        }

                    }
                });
            }
        };
        new Thread(runnable).start();
    }
}
