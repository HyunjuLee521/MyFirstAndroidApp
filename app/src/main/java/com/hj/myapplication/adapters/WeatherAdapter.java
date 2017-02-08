package com.hj.myapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hj.myapplication.R;
import com.hj.myapplication.models.Weather;

import java.util.List;

/**
 * Created by USER on 2017-02-07.
 */

public class WeatherAdapter extends BaseAdapter {
    private Context mContext;
    private List<Weather> mData;

    public WeatherAdapter(Context context, List<Weather> data) {
        mContext = context;
        mData = data;
    }


    // Adapter의 item갯수 나타내는 메서드
    @Override
    public int getCount() {
        return mData.size();
    }

    // position번째 item을 돌려주는 메서드
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    // position번째의 item의 id를 돌려주는 메서드
    // DB쓰지 않는 이상 position값 돌려주면됨
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position번째의 레이아웃(return은 View값) 돌려주는 메서드
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convertView 처음엔 null 계속 들어오면서 재사용
        // ViewGroup : 해당 어댑터를 사용하는 ListView


        ViewHolder viewholder;

        // 처음 들어왔을때
        if (convertView == null) {
            viewholder = new ViewHolder();


            // 레이아웃 붙이기 위해 가져오는 Layout - Inflater
            // Main에서너는 ContextSet ~ , 그 외에서는 Layout붙이기 위해서 LayoutInflater
            // ★ 복붙사용

            // 뷰를 새로 만들때
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_weather, parent, false);


            // 레이아웃 들고오기
            ImageView imageView = (ImageView) convertView.findViewById(R.id.weather_image);
            TextView locationTextView = (TextView) convertView.findViewById(R.id.location_text);
            TextView temperatureTextView = (TextView) convertView.findViewById(R.id.temper_text);

            // viewHolder에 넣는다
            viewholder.weatherImage = imageView;
            viewholder.locationTextView = locationTextView;
            viewholder.temperatureTextVIew = temperatureTextView;


            // ★?????
            convertView.setTag(viewholder);

        } else {
            // 재사용 할때

            // ?????
            viewholder = (ViewHolder) convertView.getTag();
        }

//        Weather weather = mData.get(position);
//        imageView.setImageResource(weather.getImageRes());
//        locationTextView.setText(weather.getLocation());
//        temperatureTextView.setText(weather.getTemperature());

        // 데이터 생성
        Weather weather = mData.get(position);

        viewholder.weatherImage.setImageResource(weather.getImageRes());
        viewholder.locationTextView.setText(weather.getLocation());
        viewholder.temperatureTextVIew.setText(weather.getTemperature());



        // 홀수 줄은 빨간색
        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.RED);
        } else {
            // 아닐 경우까지 넣어줘서
            // 매번 세팅해줘야한다
            convertView.setBackgroundColor(Color.WHITE);
        }


        // 선택된 아이템 노란색으로 바꾼다
        if (mSelectedPositon == position) {
            convertView.setBackgroundColor(Color.YELLOW);
        }

        return convertView;
    }


    // -1이면 지금 선택된 값이 없다
    private int mSelectedPositon = -1;

    // 0, 1, 2, ~ n 일 시 n 번째 positoion이 선택된것
    public void setmSelect(int position) {
        mSelectedPositon = position;
    }


    // 성능향상 위해 inner Class  ViewHolder만든다
    // findViewById로 가져온 View들을 보관
    private static class ViewHolder {
        ImageView weatherImage;
        TextView locationTextView;
        TextView temperatureTextVIew;

    }

}
