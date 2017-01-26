package com.hj.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class OrderCheckActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = OrderCheckActivity.class.getSimpleName();

    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_check);


        mResultTextView = (TextView) findViewById(R.id.result_text);

        findViewById(R.id.cancel_button).setOnClickListener(this);
        findViewById(R.id.ok_button).setOnClickListener(this);

        // getIntent() != null -> Intent를 만들어서 소환하지 않을 경우
        // 안 써도 작동은 되지만 객체지향에서 해당 Activity가 어떻게 사용될지 모르기 때문에 꼭 써주기
        if (getIntent() != null) {
            String result = getIntent().getStringExtra("result");
            String comment = getIntent().getStringExtra("comment");

            Log.d(TAG, "onCreate: " + result + " , " + comment);
            mResultTextView.setText(result + "\n\n코멘트 : " + comment);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button:
                // 현재 액티비티 종료
                finish();
                break;
            case R.id.ok_button:
                // 이메일로 쏘기
                String[] addresses = {"dlguswn920521@hanmail.net"};
                composeEmail(addresses,
                        "주문 요청",
                        mResultTextView.getText().toString());
                break;
        }
    }


    // email 보내는 메서드 : composeEmail( , ,);
    public void composeEmail(String[] addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
