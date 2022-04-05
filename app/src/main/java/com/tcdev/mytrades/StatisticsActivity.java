package com.tcdev.mytrades;

import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.tcdev.mytrades.BitsoClass.Bitso;
import com.tcdev.mytrades.TradesClass.Trades;
import com.tcdev.mytrades.TradesClass.TradesAsyncTask;
import com.tcdev.mytrades.TradesClass.TradesStatisticsAdapter;
import com.tcdev.mytrades.TradesClass.TradesStatisticsClass;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class StatisticsActivity extends AppCompatActivity {

    ArrayList<TradesStatisticsClass> arrayListStatistics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        loadListViewStatistics();

        SwipeRefreshLayout swipeRefresh = findViewById(R.id.swipeRefresh);

        swipeRefresh.setOnRefreshListener(
            new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    loadListViewStatistics();
                    swipeRefresh.setRefreshing(false);
                }
            }
        );
    }

    public void loadListViewStatistics(){
        ListView listView = findViewById(R.id.listViewStatistics);
        listView.setDivider(null);

        try {
            TradesAsyncTask asyncTask = new TradesAsyncTask();
            asyncTask.setURLPath("/api/statistics.php");
            String payload = asyncTask.execute().get();

            Trades trades = new Trades();
            arrayListStatistics = trades.getStatisticsArray(payload);

            TradesStatisticsAdapter adapter = new TradesStatisticsAdapter(this, arrayListStatistics);
            listView.setAdapter(adapter);


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}