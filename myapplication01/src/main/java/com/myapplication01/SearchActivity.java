package com.myapplication01;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.myapplication01.base.BaseActivity;
import com.myapplication01.utils.AppApplication;
import com.myapplication01.utils.Httputils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
//        ����intent����ֵ
         name = getIntent().getStringExtra("name");
//        �����յ���ֵ��ʾ���ؼ���
        title.setText(name);
        search.setOnClickListener(this);
        back.setOnClickListener(this);
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
            case R.id.search_back_but:
                startIntent(MainActivity.class);
        }
    }
    private void getDate() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                HashMap<String,Object> map = new HashMap<>();
                map.put("account", AppApplication.account);
                map.put("sport_name",name);
                final String result = Httputils.doPost("searchSport",map);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //toast(result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getInt("code") == 0) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                if (jsonArray != null) {
//                                    ��������������
                                    MyAdapter adapter = new MyAdapter(jsonArray);
//                                    ����������
                                    list.setAdapter(adapter);
                                }
                            }
                            else {
                                toast(jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            toast("json��ʽ��������");
                        }
                    }

                });
            }
        };
        new Thread(runnable).start();
    }
    /*
    * ��ײ����������
    * �Զ���������
    * */
    private class MyAdapter extends BaseAdapter {
        private JSONArray array;
        MyAdapter(JSONArray array) {
            this.array = array;
        }

        @Override
        public int getCount() {
            return array.length();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            ��������
            convertView = getLayoutInflater().inflate(R.layout.listview_item,parent,false);
            TextView startTv = (TextView) convertView.findViewById(R.id.item_start);
            TextView endTv = (TextView) convertView.findViewById(R.id.item_end);
            TextView mileage = (TextView) convertView.findViewById(R.id.item_mileage);
            try {
                startTv.setText(array.getJSONObject(position).getString("start_time"));
                endTv.setText(array.getJSONObject(position).getString("end_time"));
                mileage.setText(array.getJSONObject(position).getString("distance"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }
}
