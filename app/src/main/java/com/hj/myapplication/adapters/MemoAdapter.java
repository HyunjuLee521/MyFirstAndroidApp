package com.hj.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hj.myapplication.R;
import com.hj.myapplication.models.Memo;

import java.util.List;

/**
 * Created by USER on 2017-02-07.
 */

public class MemoAdapter extends BaseAdapter {

    private Context mContext;
    private List<Memo> mData;

    public MemoAdapter(Context context, List<Memo> mData) {
        this.mContext = context;
        this.mData = mData;
    }

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
        ViewHolder viewHolder;

        // getVIew 처음 소환될 때
        if (convertView == null) {
            viewHolder = new ViewHolder();

            // 뷰를 처음 만들 때
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_memo, parent, false);


            // ListView(R.layout.item_memo) 레이아웃 들고오기
            TextView titleTextview = (TextView) convertView.findViewById(R.id.title_textview);
            TextView contentsTextview = (TextView) convertView.findViewById(R.id.contents_textview);

            // 뷰홀더에 넣기
            viewHolder.titleTextview = titleTextview;
            viewHolder.contentsTextview = contentsTextview;

            // convetView에 viewHolder 넣기(다음번에 재사용할수 있게끔)
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        // 사용할 데이터 새로운 변수로 만들어 셋팅
        Memo memo = mData.get(position);

        // 화면에 뿌리기
        viewHolder.titleTextview.setText(memo.getTitle());
        viewHolder.contentsTextview.setText(memo.getContents());


        return convertView;
    }


    private static class ViewHolder {
        TextView titleTextview;
        TextView contentsTextview;

    }
}
