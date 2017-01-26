package com.hj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();   // 클래스 이름을 String으로 얻는 방법

    public static final int QUANTITY_MIN = 0;
    public static final int COFFEE_PRICE = 3000;
    public static final int CREAM_PRICE = 500;
    public static final int QUANTITY_MAX = 10;

    private Button mMinusButton;
    private Button mPlusButton;
    private TextView mQuantityTextView;
    private TextView mResultTextView;
    private Button mOrderButton;
    private CheckBox mCreamCheckBox;

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

        // 레이아웃에서 특정 id를 인스턴스 변수와 연결
        //  findViewById의 return type -> view 이므로
        // 하위 개념으로 casting 해줘야해
        mMinusButton = (Button) findViewById(R.id.minus_button);
        mPlusButton = (Button) findViewById(R.id.plus_button);
        mQuantityTextView = (TextView) findViewById(R.id.quantity_text);
        mResultTextView = (TextView) findViewById(R.id.result_text);
        mOrderButton = (Button) findViewById(R.id.order_button);
        mCreamCheckBox = (CheckBox) findViewById(R.id.cream_check);


        mCreamCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsCream = isChecked;
                displayResult();
            }
        });


        // control + space
        // 무명클래스
        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 개발시에는 debug Log.d 만 쓰면 됨
//                Log.d(TAG, "마이너스 버튼 클릭");
//                Log.v(TAG, "일반로그");
//                Log.e(TAG, "에러로그");
//                Log.i(TAG, "정보로그");
//                Log.w(TAG, "경고로그");
//
//                // 토스트 메세지
//                Toast.makeText(MainActivity.this,
//                        "메세지",
//                        Toast.LENGTH_SHORT).show();

                mQuantity--;
                if (mQuantity < QUANTITY_MIN) {
                    mQuantity = QUANTITY_MIN;
                }
                // setText에는 Int값 넣었을때 String 의 주소값(참조값)으로 활용
                // int값을 출력하기 위해서는 "" + int값으로 String값으로 변환시켜 출력


                displayResult();


            }
        });

        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mQuantity++;
                if (mQuantity > QUANTITY_MAX) {
                    mQuantity = QUANTITY_MAX;
                }
                // setText에는 Int값 넣었을때 String 의 주소값(참조값)으로 활용
                // int값을 출력하기 위해서는 "" + int값으로 String값으로 변환시켜 출력
                mQuantityTextView.setText("" + mQuantity);

                displayResult();


            }
        });

        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mResultTextView.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void displayResult() {
        mQuantityTextView.setText("" + mQuantity);

        int total = COFFEE_PRICE * mQuantity;
        if (mIsCream) {
            total += CREAM_PRICE;
        } else {
            total -= CREAM_PRICE;
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


}
