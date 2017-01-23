package com.hj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {    // java의 pvms같은
        super.onCreate(savedInstanceState);     // 반드시 해야 함

        // 레이아웃 표시
        setContentView(R.layout.activity_main);
        // 모든 Resource-> R. 으로 접근
    }
}
