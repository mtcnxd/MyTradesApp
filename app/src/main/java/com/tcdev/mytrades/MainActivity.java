package com.tcdev.mytrades;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import com.tcdev.mytrades.TradesClass.Trades;
import com.tcdev.mytrades.TradesClass.TradesAsyncTask;
import com.tcdev.mytrades.TradesClass.TradesTickerAdapter;
import com.tcdev.mytrades.TradesClass.TradesTickerClass;
import com.tcdev.mytrades.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TradesTickerClass> arrayListTicker;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        loadListViewTicker();

        SwipeRefreshLayout swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(
            new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    loadListViewTicker();
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
            asyncTask.setURLPath("/api/wservice.php");
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

        switch (id) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_ticker:
                Toast.makeText(this, "Ticker", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_statistics:
                Toast.makeText(this, "Loading statistics ...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, StatisticsActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}