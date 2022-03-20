package com.tcdev.mytrades.BitsoClass;

import android.os.AsyncTask;

public class BitsoTicker extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String ... strings) {
        Bitso bitso = new Bitso();
        String response = bitso.getBitsoRequest("/v3/ticker/");
        return response;
    }
}
