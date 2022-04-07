package com.tcdev.mytrades;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.tcdev.mytrades.TradesClass.Trades;
import com.tcdev.mytrades.TradesClass.TradesAsyncTask;
import com.tcdev.mytrades.TradesClass.TradesBalanceAdapter;
import com.tcdev.mytrades.TradesClass.TradesBalanceClass;
import com.tcdev.mytrades.TradesClass.TradesTickerAdapter;
import com.tcdev.mytrades.TradesClass.TradesTickerClass;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;

public class BalanceActivity extends AppCompatActivity {
    private ArrayList<TradesBalanceClass> arrayListBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        loadListViewBalance();
        loadBalanceGraph();

        SwipeRefreshLayout swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        loadListViewBalance();
                        swipeRefresh.setRefreshing(false);
                    }
                }
        );

    }

    public void loadListViewBalance(){
        ListView listViewBalance = findViewById(R.id.listViewBalance);
        listViewBalance.setDivider(null);

        try {
            TradesAsyncTask asyncTask = new TradesAsyncTask();
            asyncTask.setPath("/api/wservice.php");
            asyncTask.setData("balances");
            String payload = asyncTask.execute().get();

            Trades trades = new Trades();
            arrayListBalance = trades.getBalanceArray(payload);

            TradesBalanceAdapter adapter = new TradesBalanceAdapter (this, arrayListBalance);
            listViewBalance.setAdapter(adapter);

            int sizeOfPurchase = trades.getSizeOfPurchases();
            Toast.makeText(this, sizeOfPurchase + " active purchases", Toast.LENGTH_SHORT).show();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadBalanceGraph(){
        ColumnChartView chartView = findViewById(R.id.balanceGraph);

        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values = new ArrayList<>();

        values.add(new SubcolumnValue(10, ChartUtils.pickColor()));
        values.add(new SubcolumnValue(15, ChartUtils.pickColor()));
        values.add(new SubcolumnValue(12, ChartUtils.pickColor()));
        values.add(new SubcolumnValue(10, ChartUtils.pickColor()));
        values.add(new SubcolumnValue(15, ChartUtils.pickColor()));
        values.add(new SubcolumnValue(12, ChartUtils.pickColor()));
        values.add(new SubcolumnValue(12, ChartUtils.pickColor()));
        values.add(new SubcolumnValue(10, ChartUtils.pickColor()));

        Column column = new Column(values);
        columns.add(column);

        ColumnChartData data = new ColumnChartData(columns);
        chartView.setColumnChartData(data);
    }
}