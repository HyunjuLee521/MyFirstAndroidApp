package com.hj.myapplication;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignupActivity1 extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 1000;
    private EditText mIdEdittext;
    private EditText mPwEdittext;
    private EditText mConfirmEdittext;
    private EditText mEmailEdittext;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        mIdEdittext = (EditText) findViewById(R.id.id_edittext);
        mPwEdittext = (EditText) findViewById(R.id.pw_edittext);
        mConfirmEdittext = (EditText) findViewById(R.id.confirm_edittext);
        mEmailEdittext = (EditText) findViewById(R.id.email_edittext);

        findViewById(R.id.reset_button).setOnClickListener(this);
        findViewById(R.id.join_button).setOnClickListener(this);


        mRadioGroup = (RadioGroup) findViewById(R.id.sex_radiogroup);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_button:
                reset();
                break;

            case R.id.join_button:
                if (isNotNull() && confirmPw()) {
                    join();
                }
                break;

            default:
                break;
        }

    }

    private void reset() {
        mIdEdittext.setText("");
        mPwEdittext.setText("");
        mConfirmEdittext.setText("");
        mEmailEdittext.setText("");
    }


    // null값인지 확인하여 null이면 false값 반환하는 메서드
    public boolean isNotNull() {
        boolean result = true;
        if (TextUtils.isEmpty(mIdEdittext.getText().toString())
                || mPwEdittext.getText().toString().isEmpty()
                || mConfirmEdittext.getText().toString().isEmpty()
                || mEmailEdittext.getText().toString().isEmpty()) {
            Toast.makeText(this, "모두 입력해주셔야 합니다", Toast.LENGTH_SHORT).show();
            result = false;
        }

        return result;
    }

    // 비밀번호와 비밀번호 확인 값이 다르면 false값 반환하는 메서드
    public boolean confirmPw() {
        boolean result = true;
        if (!mPwEdittext.getText().toString().equals(mConfirmEdittext.getText().toString())) {
            Toast.makeText(this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
            result = false;
        }

        return result;

    }


    // 가입 버튼을 눌렀을 때
    // Intent실행하는 메서드
    public void join() {
        Intent intent = new Intent(this, SignupActivity2.class);
        intent.putExtra("id", mIdEdittext.getText().toString());
        intent.putExtra("pw", mPwEdittext.getText().toString());
        intent.putExtra("email", mEmailEdittext.getText().toString());

        String mGender = ((RadioButton) findViewById(mRadioGroup.getCheckedRadioButtonId())).getText().toString();
//        if (R.id.female_radiobutton == mRadioGroup.getCheckedRadioButtonId()) {
//            intent.putExtra("sex", "여자");
//        } else {
//            intent.putExtra("sex", "남자");
//        }

        intent.putExtra("sex", mGender);

        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE
                && resultCode == RESULT_OK) {
            Toast.makeText(this, "확인 버튼을 누르셨습니다", Toast.LENGTH_SHORT).show();
        }

    }
}
