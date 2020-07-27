package com.anningtex.notificationmanger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Administrator
 * desc:Notification
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mOne;
    private Button mTwo;
    private Button mThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mOne = findViewById(R.id.one);
        mOne.setOnClickListener(this);
        mTwo = findViewById(R.id.two);
        mTwo.setOnClickListener(this);
        mThree = findViewById(R.id.three);
        mThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.one:
                startActivity(new Intent(MainActivity.this, OneActivity.class));
                break;
            case R.id.two:
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
                break;
            case R.id.three:
                //android8.0 通知栏不显示、无横幅通知问题解决
                startActivity(new Intent(MainActivity.this, ThreeActivity.class));
                break;
        }
    }
}
