package com.tcdev.mytrades.TradesClass;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
            v = inflater.inflate(R.layout.listview_item, null);
        }

        TradesTickerClass dir = items.get(i);

        TextView book = v.findViewById(R.id.book);
        book.setText(dir.getBook());

        TextView price = v.findViewById(R.id.price);
        price.setText(dir.getPrice());

        TextView date = v.findViewById(R.id.date);
        date.setText(dir.getDate());

        TextView currency  = v.findViewById(R.id.currency);
        currency.setText(dir.getCurrency());

        return v;
    }
}
