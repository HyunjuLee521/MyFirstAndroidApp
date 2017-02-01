package com.hj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity2 extends AppCompatActivity implements View.OnClickListener {


    private TextView mIdTextview;
    private TextView mPwTextview;
    private TextView mEmailTextview;
    private TextView mSexTextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        if (getIntent() != null) {
            String id = getIntent().getStringExtra("id");
            String pw = getIntent().getStringExtra("pw");
            String email = getIntent().getStringExtra("email");
            String sex = getIntent().getStringExtra("sex");

//            Toast.makeText(this, id + " " + pw + " " + email + " " + sex, Toast.LENGTH_SHORT).show();

            mIdTextview = (TextView) findViewById(R.id.id_textview);
            mPwTextview = (TextView) findViewById(R.id.pw_textview);
            mEmailTextview = (TextView) findViewById(R.id.email_textview);
            mSexTextview = (TextView) findViewById(R.id.sex_textview);

            mIdTextview.setText(id);
            mPwTextview.setText(pw);
            mEmailTextview.setText(email);
            mSexTextview.setText(sex);

            findViewById(R.id.check_button).setOnClickListener(this);

        }

    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent();
        setResult(RESULT_OK);
        finish();
    }
}
