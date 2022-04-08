package com.tcdev.mytrades.TradesClass;

import android.os.AsyncTask;
import android.util.Log;

public class TradesAsyncTask extends AsyncTask<String, String, String> {

    String path;
    String data;

    public void setPath(String path) {
        this.path = path;
    }

    public void setData(String data){
        this.data = data;
    }

    @Override
    protected String doInBackground(String ... strings) {
        Trades trades = new Trades();
        String response = trades.getTradesRequest(path, data);
        Log.d("Mensaje", "doInBackground: " + response);
        return response;
    }
}
