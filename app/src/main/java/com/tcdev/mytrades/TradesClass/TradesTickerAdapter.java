package com.tcdev.mytrades.TradesClass;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcdev.mytrades.R;

import java.util.ArrayList;

public class TradesTickerAdapter extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<TradesTickerClass> items;

    public TradesTickerAdapter(Activity activity, ArrayList<TradesTickerClass> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listview_item_mainlist, null);
        }

        TradesTickerClass dir = items.get(i);

        TextView percent = v.findViewById(R.id.percent);
        percent.setText(dir.getPercent());

        String changePercent = dir.getPercent();
        changePercent = changePercent.replace('%','0');

        if(Double.valueOf(changePercent) < 0) {
            percent.setTextColor(Color.parseColor("#ff0000"));
        } else {
            percent.setTextColor(Color.parseColor("#51ff00"));
        }

        TextView price = v.findViewById(R.id.current);
        price.setText(dir.getCurrent());

        TextView last = v.findViewById(R.id.last);
        last.setText(dir.getLast());

        TextView date = v.findViewById(R.id.date);
        date.setText(dir.getDate());

        TextView currency  = v.findViewById(R.id.currency);
        currency.setText(dir.getCurrency());

        LinearLayout layout = v.findViewById(R.id.listview_item_background);

        if (i%2 == 0)
            layout.setBackgroundColor(Color.parseColor("#F5F5F5"));
        else
            layout.setBackgroundColor(Color.parseColor("#ffffff"));

        return v;
    }
}
