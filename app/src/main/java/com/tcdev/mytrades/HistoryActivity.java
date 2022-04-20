package com.tcdev.mytrades;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.tcdev.mytrades.TradesClass.Trades;
import com.tcdev.mytrades.TradesClass.TradesAsyncTask;
import com.tcdev.mytrades.TradesClass.TradesBalanceAdapter;
import com.tcdev.mytrades.TradesClass.TradesBalanceClass;
import com.tcdev.mytrades.databinding.ActivityHistoryBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HistoryActivity extends AppCompatActivity {
    private ArrayList<TradesBalanceClass> arrayListBalance;
    private ActivityHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        loadListViewBalance();

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void loadListViewBalance(){
        ListView listViewBalance = findViewById(R.id.listViewHistory);
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
}