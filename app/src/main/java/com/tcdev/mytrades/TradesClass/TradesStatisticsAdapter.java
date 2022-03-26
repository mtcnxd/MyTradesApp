package com.tcdev.mytrades.TradesClass;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tcdev.mytrades.R;

import java.util.ArrayList;

public class TradesStatisticsAdapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<TradesStatisticsClass> items;

    public TradesStatisticsAdapter(Activity activity, ArrayList<TradesStatisticsClass> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
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
            v = inflater.inflate(R.layout.listview_item_statistics, null);
        }

        TradesStatisticsClass dir = items.get(i);

        TextView book = v.findViewById(R.id.key_left);
        book.setText(dir.getKeyLeft());

        TextView price = v.findViewById(R.id.value_left);
        price.setText(dir.getValueLeft());

        TextView date = v.findViewById(R.id.key_right);
        date.setText(dir.getKeyRight());

        TextView currency  = v.findViewById(R.id.value_right);
        currency.setText(dir.getValueRight());

        return v;
    }
}
