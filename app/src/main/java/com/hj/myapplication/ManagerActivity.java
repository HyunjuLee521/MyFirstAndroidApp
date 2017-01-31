package com.hj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        if (getIntent() != null) {
            String idValue = getIntent().getStringExtra("id");
            String pwValue = getIntent().getStringExtra("pw");
            Toast.makeText(this, "id : " + idValue + " pw : " + pwValue, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View v) {

        String select = ((Button) v).getText().toString();
        Intent intent = new Intent();

        intent.putExtra("select", select);
        setResult(RESULT_OK, intent);
        finish();

    }
}
