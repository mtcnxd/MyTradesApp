package com.tcdev.mytrades;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import com.tcdev.mytrades.TradesClass.Trades;
import com.tcdev.mytrades.TradesClass.TradesAsyncTask;
import com.tcdev.mytrades.TradesClass.TradesTickerAdapter;
import com.tcdev.mytrades.TradesClass.TradesTickerClass;
import com.tcdev.mytrades.databinding.ActivityMainBinding;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TradesTickerClass> arrayListTicker = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        loadListViewTicker();
        loadBalanceHistory();

        SwipeRefreshLayout swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(
            new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    loadListViewTicker();
                    loadBalanceHistory();
                    swipeRefresh.setRefreshing(false);
                }
            }
        );

        binding.fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace testing", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }

    public void loadListViewTicker (){
        ListView listViewTicker = findViewById(R.id.listViewTicker);
        listViewTicker.setDivider(null);

        try {
            TradesAsyncTask asyncTask = new TradesAsyncTask();
            asyncTask.setPath("/api/wservice.php");
            asyncTask.setData("mainList");
            String payload = asyncTask.execute().get();


            Trades trades = new Trades();
            arrayListTicker = trades.getTickerArray(payload);

            TradesTickerAdapter adapter = new TradesTickerAdapter (this, arrayListTicker);
            listViewTicker.setAdapter(adapter);

            int sizeOfPurchase = trades.getSizeOfPurchases();
            Toast.makeText(this, sizeOfPurchase + " active purchases", Toast.LENGTH_SHORT).show();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (id) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_ticker:
                Toast.makeText(this, "Ticker", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_statistics:
                Toast.makeText(this, "Loading statistics ...", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, StatisticsActivity.class);
                startActivity(intent);
                break;

            case R.id.action_balance:
                Toast.makeText(this, "Loading balance ...", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, BalanceActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadBalanceHistory(){
        try {
            TradesAsyncTask asyncTask = new TradesAsyncTask();
            asyncTask.setPath("/api/wservice.php");
            asyncTask.setData("chartdata");
            String payload = asyncTask.execute().get();

            LineChartView chartView = findViewById(R.id.balanceHistory);

            Trades trades = new Trades();
            List<PointValue> values = trades.getChartDataBalances(payload);

            Line line = new Line(values);
            line.setColor(Color.parseColor("#fcba03"));
            line.setCubic(true);
            line.setFilled(true);
            line.setStrokeWidth(1);
            line.setPointRadius(2);

            Axis axisX = new Axis();
            axisX.setHasLines(true);

            Axis axisY = new Axis();
            axisY.setHasLines(true);

            List<Line> lines = new ArrayList();
            lines.add(line);

            LineChartData data = new LineChartData(lines);
            data.setLines(lines);
            //data.setAxisYLeft(axisX);
            data.setAxisXBottom(axisY);

            LineChartView chart = new LineChartView(this);
            chart.setLineChartData(data);
            chartView.setLineChartData(data);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}