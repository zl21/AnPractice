package com.myapplication01;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.myapplication01.base.BaseActivity;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button add,run,exercise,walk,bike,swim,ball,night;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        add.setOnClickListener(this);
        run.setOnClickListener(this);
        exercise.setOnClickListener(this);
        walk.setOnClickListener(this);
        bike.setOnClickListener(this);
        swim.setOnClickListener(this);
        ball.setOnClickListener(this);
        night.setOnClickListener(this);
    }

    private void init() {
        add = (Button) findViewById(R.id.selece_add_but);
        run = (Button) findViewById(R.id.select_morningRun_but);
        exercise = (Button) findViewById(R.id.select_exercise_but);
        walk = (Button) findViewById(R.id.select_walk_but);
        bike = (Button) findViewById(R.id.select_bike_but);
        swim = (Button) findViewById(R.id.select_swim_but);
        ball = (Button) findViewById(R.id.select_ball_but);
        night = (Button) findViewById(R.id.select_night_but);
    }

    @Override
    public void onClick(View v) {
//        携带数据的跳转
        Intent intent = new Intent(this,SearchActivity.class);
        switch (v.getId()) {
            case R.id.selece_add_but:
                break;
            case R.id.select_morningRun_but:
                intent.putExtra("name","晨跑");
                break;
            case R.id.select_exercise_but:
                intent.putExtra("name","晨练");
                break;
            case R.id.select_walk_but:
                intent.putExtra("name","日间行走");
                break;
            case R.id.select_bike_but:
                intent.putExtra("name","骑行");
                break;
            case R.id.select_swim_but:
                intent.putExtra("name","游泳");
                break;
            case R.id.select_ball_but:
                intent.putExtra("name","球类");
                break;
            case R.id.select_night_but:
                intent.putExtra("name","晚间跑步");
                break;
        }
        if (v.getId() != R.id.selece_add_but) {
        //        启动意图对象
            startActivity(intent);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
