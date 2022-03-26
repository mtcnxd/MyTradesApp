package com.tcdev.mytrades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.tcdev.mytrades.TradesClass.Trades;
import com.tcdev.mytrades.TradesClass.TradesStatisticsAdapter;
import com.tcdev.mytrades.TradesClass.TradesStatisticsClass;

import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ListView listView = findViewById(R.id.ListViewStatistics);

        ArrayList<TradesStatisticsClass> arrayListStatistics = new ArrayList<>();
        arrayListStatistics.add(new TradesStatisticsClass("CURRENT PERFORMANCE","0.06%","PERFORMANCE LAST 24 HOURS","1.13%"));
        arrayListStatistics.add(new TradesStatisticsClass("BUYING POWER (0.00%)","$0.12","TOTAL WALLET","$30,622.62"));
        arrayListStatistics.add(new TradesStatisticsClass("CURRENT PERFORMANCE","0.06%","PERFORMANCE LAST 24 HOURS","1.13%"));
        arrayListStatistics.add(new TradesStatisticsClass("BUYING POWER (0.00%)","$0.12","TOTAL WALLET","$30,622.62"));

        TradesStatisticsAdapter adapter = new TradesStatisticsAdapter(this, arrayListStatistics);
        listView.setAdapter(adapter);
    }
}