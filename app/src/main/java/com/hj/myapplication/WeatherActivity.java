package com.hj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hj.myapplication.adapters.WeatherAdapter;
import com.hj.myapplication.models.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private WeatherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mListView = (ListView) findViewById(R.id.list_view);

        // 날씨 데이터 만들기
        List<Weather> weatherList = new ArrayList<>();

        weatherList.add(new Weather(R.drawable.man, "수원", "27도"));
        weatherList.add(new Weather(R.drawable.man, "서울", "21도"));
        weatherList.add(new Weather(R.drawable.man, "도쿄", "8도"));
        weatherList.add(new Weather(R.drawable.man, "부산", "-2도"));
        weatherList.add(new Weather(R.drawable.man, "평택", "18도"));
        weatherList.add(new Weather(R.drawable.man, "안산", "-16도"));
        weatherList.add(new Weather(R.drawable.man, "하와이", "27도"));
        weatherList.add(new Weather(R.drawable.man, "인천", "21도"));
        weatherList.add(new Weather(R.drawable.man, "울산", "8도"));
        weatherList.add(new Weather(R.drawable.man, "부산", "-23도"));
        weatherList.add(new Weather(R.drawable.man, "평택", "12도"));
        weatherList.add(new Weather(R.drawable.man, "안산", "-15도"));
        weatherList.add(new Weather(R.drawable.man, "수원", "27도"));
        weatherList.add(new Weather(R.drawable.man, "서울", "21도"));
        weatherList.add(new Weather(R.drawable.man, "도쿄", "8도"));
        weatherList.add(new Weather(R.drawable.man, "부산", "-2도"));
        weatherList.add(new Weather(R.drawable.man, "평택", "18도"));
        weatherList.add(new Weather(R.drawable.man, "안산", "-16도"));
        weatherList.add(new Weather(R.drawable.man, "하와이", "27도"));
        weatherList.add(new Weather(R.drawable.man, "인천", "21도"));
        weatherList.add(new Weather(R.drawable.man, "울산", "8도"));
        weatherList.add(new Weather(R.drawable.man, "부산", "-23도"));
        weatherList.add(new Weather(R.drawable.man, "평택", "12도"));
        weatherList.add(new Weather(R.drawable.man, "안산", "-15도"));

        // 커스텀 어댑터 만들기
        mAdapter = new WeatherAdapter(this, weatherList);

        // ListView에 어댑터 꽂아주기
        mListView.setAdapter(mAdapter);

        // ListVIew의 아이템을 클릭했을 때
        mListView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.setmSelect(position);

        // 데이터가 변경됨을 알려줌 = 다시 그려라
        // -> 바로 getVIew메서드 호출하도록
        mAdapter.notifyDataSetChanged();
    }
}
