package com.hj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TargetActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView mValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        mValueTextView = (TextView) findViewById(R.id.value_text);
        findViewById(R.id.result_button).setOnClickListener(this);

        if (getIntent() != null) {
            String value = getIntent().getStringExtra("value");
            mValueTextView.setText(value);
        }
    }

    @Override
    public void onClick(View v) {
        // 결과만 전달 할 때는
//        // 둘중 하나 사용 OK -1 , Cancle 0
//        setResult(RESULT_OK);
////        setResult(RESULT_CANCELED);
//        finish();

        // 결과와 데이터 둘다 전달할 떄는
        Intent intent = new Intent();
        intent.putExtra("result", "이것은 내가 지정한 문구다.");
        intent.putExtra("int", 50);

        setResult(RESULT_OK, intent);   // 결과값과 data(Intent)모두 전달

        finish();   // 전달 한 후 해당 액티비티는 종료 -> 쌓이지 않도록

    }
}
