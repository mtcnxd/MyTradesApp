package com.tcdev.mytrades;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.tcdev.mytrades.BitsoClass.Bitso;
import com.tcdev.mytrades.TradesClass.Trades;
import com.tcdev.mytrades.TradesClass.TradesAsyncTask;
import com.tcdev.mytrades.TradesClass.TradesStatisticsAdapter;
import com.tcdev.mytrades.TradesClass.TradesStatisticsClass;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class StatisticsActivity extends AppCompatActivity {

    ArrayList<TradesStatisticsClass> arrayListStatistics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ListView listView = findViewById(R.id.ListViewStatistics);
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