package com.hj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Activity 이름을 String으로 얻는 방법
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final int QUANTITY_MIN = 0;
    public static final int QUANTITY_MAX = 10;
    public static final int COFFEE_PRICE = 3000;
    public static final int CREAM_PRICE = 500;

    private TextView mQuantityTextView;
    private TextView mResultTextView;
    private CheckBox mCreamCheckBox;
    private EditText mCommentEidtText;


    // 수량 TextView는 숫자 타입으로 다룰 수 있는것이 아니라, 그냥 보여주는
    private int mQuantity;  // 초기화 안되어있음

    // 휘핑크림 변수
    private boolean mIsCream;   //  boolean초기화안해도 됨 기본값false


    @Override
    protected void onCreate(Bundle savedInstanceState) {    // java의 pvms같은
        super.onCreate(savedInstanceState);     // 반드시 해야 함

        // 초기화
        init();

        // 레이아웃 표시
        setContentView(R.layout.layout_exam3);
        // 모든 Resource-> R. 으로 접근
        // - 이렇게 로드한 순간 layout의 객체들 이미 인스턴스화 됨


        mCreamCheckBox = (CheckBox) findViewById(R.id.cream_check);
        mQuantityTextView = (TextView) findViewById(R.id.quantity_text);
        mResultTextView = (TextView) findViewById(R.id.result_text);
        mCommentEidtText = (EditText) findViewById(R.id.comment_edit);


//        // 레이아웃에서 특정 id를 인스턴스 변수와 연결
//        //  findViewById의 return type -> view 이므로
//        // 하위 개념으로 casting 해줘야해
//        mMinusButton = (Button) findViewById(R.id.minus_button);
//        mPlusButton = (Button) findViewById(R.id.plus_button);
//        mOrderButton = (Button) findViewById(R.id.order_button);


        findViewById(R.id.plus_button).setOnClickListener(this);
        findViewById(R.id.minus_button).setOnClickListener(this);
        findViewById(R.id.order_button).setOnClickListener(this);


        mCreamCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsCream = isChecked;
                displayResult();
            }
        });


    }


    private void displayResult() {
        mQuantityTextView.setText("" + mQuantity);
        int total = COFFEE_PRICE * mQuantity;
        if (mIsCream) {
            total += CREAM_PRICE * mQuantity;
        }

        String result = String.format("가격 %d원\n수량 %d개\n휘핑크림 : %s\n감사합니다",
                total,
                mQuantity,
                mIsCream);
        mResultTextView.setText(result);
    }


    private void init() {
        mQuantity = 0;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus_button:
                mQuantity++;
                if (mQuantity > QUANTITY_MAX) {
                    mQuantity = QUANTITY_MAX;
                }
                displayResult();
                break;

            case R.id.minus_button:
                mQuantity--;
                if (mQuantity < QUANTITY_MIN) {
                    mQuantity = QUANTITY_MIN;
                }
                displayResult();
                break;

            case R.id.order_button:
                String message = mResultTextView.getText().toString();

                // OrderCheckActivity 화면을 시작
                Intent intent = new Intent(this, OrderCheckActivity.class);
                // 데이터 담기
                intent.putExtra("result", message);
                intent.putExtra("comment", mCommentEidtText.getText().toString());
                startActivity(intent);
                break;

            default:
                break;


        }

    }
}
