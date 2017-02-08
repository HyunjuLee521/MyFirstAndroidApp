package com.hj.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<Map<String, Object>> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        // 리스트뷰
        mListView = (ListView) findViewById(R.id.list_view);

        // 데이터 생성
        mDataList = new ArrayList<>();

        addItem("농구 점수판", "Button, OnClickListener 연습", BasketballActivity.class);
        addItem("커피앱", "CheckBox", MainActivity.class);
        addItem("회원가입", "RadioButton, StartActivityForResult 연습", SignupActivity1.class);
        addItem("인텐트 주고 받기", "StartActivityForResult", StartActivityForResultActivity.class);
        addItem("날씨앱", "모델클래스를 활용하여 BaseAdapter 연습", WeatherActivity.class);
        addItem("메모장", "메모", Memo1Activity.class);


        // 어댑터 커스터마이징
        MyAdapter adapter = new MyAdapter(mDataList);

        // 레이아웃의 리스트뷰에 어댑터 연결
        mListView.setAdapter(adapter);


        // mListview의 이벤트
        // 클릭시 이벤트
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Intent intent = (Intent) map.get("intent");
                startActivity(intent);
            }
        });

        // 롱클릭시 이벤트
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "롱클릭" + position, Toast.LENGTH_SHORT).show();
                // 처리했음을 나타내는 true값 반환
                return true;
            }
        });

    }


    private void addItem(String title, String desc, Class cls) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("desc", desc);
        map.put("intent", new Intent(this, cls));
        mDataList.add(map);
    }


    // Adapter 만들기
    private static class MyAdapter extends BaseAdapter {
        private final List<Map<String, Object>> mData;

        // data를 받아오는 Adapter 생성자
        public MyAdapter(List<Map<String, Object>> mData) {
            this.mData = mData;
        }

        // 메서드
        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext())
                        // 안드로이드에서 제공하는 레이아웃 중 simple_list_item_2 사용
                        .inflate(android.R.layout.simple_list_item_2, parent, false);
            }

            // simple_list_item_2의 TextView 연결
            TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
            TextView text2 = (TextView) convertView.findViewById(android.R.id.text2);

            // 생성시 받아온 데이터 mData값 가져오기
            Map<String, Object> item = mData.get(position);

            // mData값을 위에서 연결한 simple_list_item_2의 TextView에 set한다
            text1.setText((String) item.get("title"));
            text2.setText((String) item.get("desc"));

            // convertView(View)를 반환
            return convertView;
        }


    }
}
