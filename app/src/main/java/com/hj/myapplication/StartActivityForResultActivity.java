package com.hj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivityForResultActivity extends AppCompatActivity implements View.OnClickListener {


    public static final int REQUEST_CODE_EXAMPLE = 1000;
    private static final String TAG = StartActivityForResultActivity.class.getSimpleName();
    private EditText mValueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_for_result);

        mValueEditText = (EditText) findViewById(R.id.value_edit);

        findViewById(R.id.submit_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, TargetActivity.class);
        intent.putExtra("value", mValueEditText.getText().toString());

//        startActivity(intent);
        // 값 주거니 받거니 하고 싶을 땐 startActivity가 아니라
        startActivityForResult(intent, REQUEST_CODE_EXAMPLE);   // 게스트코드
    }


    // target으로부터 값 받기 위한 오버라이드
    // 받을 때 호출되는 콜백 메서드
    // (콜백 메서드 - 명시적으로 실행한적이 없지만, 어떤 이벤트에 의해 알아서 실행됨 ex. onClick도 콜백메서드)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // request 위에서 지정한 1000번, resultCode RESULT_OK, Intent(data)
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EXAMPLE && resultCode == RESULT_OK && data != null) {

            Log.d(TAG, "onActivityResult: " + requestCode);
            Log.d(TAG, "onActivityResult: " + resultCode);
            Log.d(TAG, "onActivityResult: " + data);

            String result = data.getStringExtra("result");
            int value = data.getIntExtra("int", -1);
            Toast.makeText(this, result + ", int: " + value, Toast.LENGTH_SHORT).show();
        }


    }
}
