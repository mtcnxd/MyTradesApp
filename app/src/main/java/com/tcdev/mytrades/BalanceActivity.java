package com.tcdev.mytrades;

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
import java.util.concurrent.ExecutionException;

public class BalanceActivity extends AppCompatActivity {
    private ArrayList<TradesBalanceClass> arrayListBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        loadListViewBalance();
    }

    public void loadListViewBalance (){
        ListView listViewBalance = findViewById(R.id.listViewBalance);
        listViewBalance.setDivider(null);

        try {
            TradesAsyncTask asyncTask = new TradesAsyncTask();
            asyncTask.setURLPath("/api/wservice.php");
            String payload = asyncTask.execute().get();

            Log.d("Mensaje","Payload: " + payload);

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