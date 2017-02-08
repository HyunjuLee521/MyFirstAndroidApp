package com.hj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Memo2Activity extends AppCompatActivity {
    private EditText mContentsEdittext;
    private EditText mTitleEdittext;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo2);

        mContentsEdittext = (EditText) findViewById(R.id.contents_edittext);
        mTitleEdittext = (EditText) findViewById(R.id.title_edittext);


        int intentId = -1;
        if (getIntent() != null) {
            // Memo1Activity startActivityForResult의 requestcode가 REQUEST_CREATE_NEW_MEMO 일 때,
            // = Memo1Activity에서 id값을 보내왔다면
            // -> id 값 intentId 에 반환

            // Memo1Activity startActivityForResult의 requestcode가  REQUEST_REVISE_EXIST_MEMO 일 때,
            // = Memo1Activity에서 id값을 보내오지 않았다면
            // -> id 값에 -1 할당

            intentId = getIntent().getIntExtra("id", -1);
        }

        // Memo1Activity startActivityForResult의 requestcode가 REQUEST_CREATE_NEW_MEMO 일 때.
        if (intentId != -1) {
            String contents = getIntent().getStringExtra("contents");
            String title = getIntent().getStringExtra("title");
            id = intentId;

            mContentsEdittext.setText(contents);
            mTitleEdittext.setText(title);
        }


    }


    // menu_memo를 붙이는 메서드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_memo, menu);

        return super.onCreateOptionsMenu(menu);
    }

    Intent intent = new Intent();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cancel:
                cancel();
                return true;

            case R.id.action_save:
                save();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void cancel() {
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    private void save() {
        intent.putExtra("title", mTitleEdittext.getText().toString());
        intent.putExtra("contents", mContentsEdittext.getText().toString());
        intent.putExtra("id", id);

        setResult(RESULT_OK, intent);
        finish();
    }


}
