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
import android.widget.Toast;

import com.hj.myapplication.adapters.MemoAdapter;
import com.hj.myapplication.models.Memo;

import java.util.ArrayList;
import java.util.List;

public class Memo1Activity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    public static final int REQUEST_CREATE_NEW_MEMO = 1000;
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

        // ★ 무명 클래스 내에서 this -> 자기 자신(무명클래스) 가르키게 됨
        fab.setOnClickListener(this);


        // 레이아웃의 ListView 연결결
        mListView = (ListView) findViewById(R.id.list_view);

        // Memo data 생성
        mMemoList = new ArrayList<>();

        // 커스텀 어댑터 MemoAdapter 생성
        mAdapter = new MemoAdapter(mMemoList);

        // ListView에 어댑터 꽂기
        mListView.setAdapter(mAdapter);

        // ListVIew(기존에 작성된 메모)가 클릭 될 때
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


        if (data != null && resultCode == RESULT_OK) {
            // 저장버튼을 눌렀을 때

            String title = data.getStringExtra("title");
            String contents = data.getStringExtra("contents");
            Memo memo = new Memo(title, contents);

            if (requestCode == REQUEST_CREATE_NEW_MEMO) {
                // + 버튼을 눌러 새로운 메모를 생성한 경우
                mMemoList.add(memo);

            } else if (requestCode == REQUEST_REVISE_EXIST_MEMO) {
                // 기존의 메모를 눌러 (수정하고) 저장한 경우
                int id = data.getIntExtra("id", -1);
                mMemoList.set(id, memo);

            }

            mAdapter.notifyDataSetChanged();
            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();


        } else if (resultCode == RESULT_CANCELED) {
            // 취소 버튼을 눌렀을 때

            Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
        }


//
//        // ★ 줄일 수 있는 코드
//        if (requestCode == REQUEST_CREATE_NEW_MEMO && resultCode == RESULT_OK && data != null) {
//            String title = data.getStringExtra("title");
//            String contents = data.getStringExtra("contents");
//
//            mMemoList.add(new Memo(title, contents));
//            mAdapter.notifyDataSetChanged();
//            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
//
//
//        } else if (requestCode == REQUEST_REVISE_EXIST_MEMO && resultCode == RESULT_OK && data != null) {
//            String title = data.getStringExtra("title");
//            String contents = data.getStringExtra("contents");
//            int id = data.getIntExtra("id", -1);
//
//            // data(Memo)에 받아온 data(title, contents)값 세팅
//            Memo memo = new Memo(title, contents);
//
//            // ArrayList에 저장되어 있는 해당 data(Memo) 값 수정
//            mMemoList.set(id, memo);
//
//            mAdapter.notifyDataSetChanged();
//
//            // 토스트 뿌려주기
//            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
//
//        } else {
//            Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Memo memo = mMemoList.get(position);

        Intent intent = new Intent(this, Memo2Activity.class);
//        intent.putExtra("title", memo.getTitle());
//        intent.putExtra("contents", memo.getContents());

        // Memo 객체가 Serializable 상속 받았음 -> 직렬화되었음
        // Memo type 의 memo 로 한번에 넘길 수 있다
        intent.putExtra("memo", memo);

        intent.putExtra("id", position);
        startActivityForResult(intent, REQUEST_REVISE_EXIST_MEMO);

    }
}
