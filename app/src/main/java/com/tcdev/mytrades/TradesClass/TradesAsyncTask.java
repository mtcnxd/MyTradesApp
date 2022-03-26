package com.tcdev.mytrades.TradesClass;

import android.os.AsyncTask;
import android.util.Log;

import com.tcdev.mytrades.BitsoClass.Bitso;

public class TradesAsyncTask extends AsyncTask<String, String, String> {

    String path;

    public void setURLPath(String path) {
        this.path = path;
    }

    @Override
    protected String doInBackground(String ... strings) {
        Trades trades = new Trades();
        String response = trades.getTradesRequest(path);
        return response;
    }
}
