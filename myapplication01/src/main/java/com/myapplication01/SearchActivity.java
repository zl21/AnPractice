package com.myapplication01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.myapplication01.base.BaseActivity;
import com.myapplication01.utils.HttpUtils;

import java.util.HashMap;

/**
 * Created by zhoul on 2019/1/2.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private Button back,search,start,end;
    private ListView list;
    private TextView title;
    private String name;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
//        接收intent传的值
        String name = getIntent().getStringExtra("name");
//        将接收到的值显示到控件上
        title.setText(name);
        search.setOnClickListener(this);
    }

    private void init() {
        back = (Button) findViewById(R.id.search_back_but);
        search = (Button) findViewById(R.id.search_but);
        start = (Button) findViewById(R.id.search_start_but);
        end = (Button) findViewById(R.id.search_end_but);
        list = (ListView) findViewById(R.id.search_listview);
        title = (TextView) findViewById(R.id.search_title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_but:
                getDate();
                break;
        }
    }
    private void getDate() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                HashMap<String,Object> map = new HashMap<>();
                map.put("account",id);
                map.put("account",name);
                final String result = Httputils.doPost("SearchSport",map);

            }
        };
        new Thread(runnable).start();
    }
}
