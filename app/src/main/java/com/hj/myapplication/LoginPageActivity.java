package com.hj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPageActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 1000;
    private EditText mIdEdittext;
    private EditText mPwEdittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        mIdEdittext = (EditText) findViewById(R.id.id_edittext);
        mPwEdittext = (EditText) findViewById(R.id.pw_edittext);

        findViewById(R.id.login_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ManagerActivity.class);

        intent.putExtra("id", mIdEdittext.getText().toString());
        intent.putExtra("pw", mPwEdittext.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String result = data.getStringExtra("select");
            Toast.makeText(this, result + "", Toast.LENGTH_SHORT).show();
        }


    }
}
