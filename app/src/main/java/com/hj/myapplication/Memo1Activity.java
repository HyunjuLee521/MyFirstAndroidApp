package com.hj.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hj.myapplication.adapters.MemoAdapter;
import com.hj.myapplication.models.Memo;

import java.util.ArrayList;
import java.util.List;

public class Memo1Activity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    public static final int REQUEST_CREATE_NEW_MEMO= 1000;
    public static final int REQUEST_REVISE_EXIST_MEMO = 1001;
    private ListView mListView;
    private MemoAdapter mAdapter;
    private List<Memo> mMemoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(this, Memo2Activity.class);
//                startActivityForResult(intent, REQUEST_CODE);
//
//
////
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//            }
//        });

        fab.setOnClickListener(this);


        // 레이아웃의 ListView 연결결
        mListView = (ListView) findViewById(R.id.list_view);

        // Memo data 생성
        mMemoList = new ArrayList<>();
//        mMemoList.add(new Memo("테스트 타이틀1", "테스트 콘텐츠1"));
//        mMemoList.add(new Memo("테스트 타이틀1", "테스트 콘텐츠2"));
//        mMemoList.add(new Memo("테스트 타이틀1", "테스트 콘텐츠3"));
//        mMemoList.add(new Memo("테스트 타이틀1", "테스트 콘텐츠4"));


        // 커스텀 어댑터 MemoAdapter 생성
        mAdapter = new MemoAdapter(this, mMemoList);

        // ListView에 어댑터 꽂기
        mListView.setAdapter(mAdapter);

        // ListVIew가 클릭 될 때
        // 해당 item(메모) 편집으로 넘어감
        mListView.setOnItemClickListener(this);


    }


    // 버튼 클릭시 소환되는 onClick메서드
    // Memo2로 넘어감
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Memo2Activity.class);
        startActivityForResult(intent, REQUEST_CREATE_NEW_MEMO);

    }


    // Memo2로 부터 되돌아오는 callBack 메서드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CREATE_NEW_MEMO && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String contents = data.getStringExtra("contents");

            mMemoList.add(new Memo(title, contents));
            mAdapter.notifyDataSetChanged();

        } else if (requestCode == REQUEST_REVISE_EXIST_MEMO && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String contents = data.getStringExtra("contents");
            int id = data.getIntExtra("id", -1);

            // data(Memo)에 받아온 data(title, contents)값 세팅
            Memo memo = new Memo(title, contents);

            // ArrayList에 저장되어 있는 해당 data(Memo) 값 수정
            mMemoList.set(id, memo);

            mAdapter.notifyDataSetChanged();

        } else {

        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, Memo2Activity.class);
        intent.putExtra("id", position);
        intent.putExtra("title", mMemoList.get(position).getTitle());
        intent.putExtra("contents", mMemoList.get(position).getContents());

        startActivityForResult(intent, REQUEST_REVISE_EXIST_MEMO);

    }
}
