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

public class TradesBalanceAdapter extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<TradesBalanceClass> items;

    public TradesBalanceAdapter (Activity activity, ArrayList<TradesBalanceClass> items){
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
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listview_item_balance, null);
        }

        TradesBalanceClass dir = items.get(i);

        TextView currency = v.findViewById(R.id.currency);
        currency.setText(dir.getCurrency());

        TextView amount = v.findViewById(R.id.amount);
        amount.setText(dir.getAmount());

        TextView value = v.findViewById(R.id.value);
        value.setText(dir.getValue());

        return v;
    }
}
