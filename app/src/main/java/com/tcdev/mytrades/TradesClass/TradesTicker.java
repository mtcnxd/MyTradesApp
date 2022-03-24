package com.tcdev.mytrades.TradesClass;

import android.os.AsyncTask;

import com.tcdev.mytrades.BitsoClass.Bitso;

public class TradesTicker extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String ... strings) {
        Trades trades = new Trades();
        String response = trades.getTradesRequest("/api/wservice.php");
        return response;
    }
}
