package com.tcdev.mytrades.TradesClass;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Trades {
    protected String baseUrl = "https://test.fortech.mx";
    protected int sizeOfPurchases = 0;

    public int getSizeOfPurchases(){
        return sizeOfPurchases;
    }

    public String getTradesRequest(String RequestPath)
    {
        try {
            String url = baseUrl + RequestPath;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("User-Agent", "Android Trades Application");
            con.setRequestMethod("GET");

            InputStreamReader input = new InputStreamReader(con.getInputStream());
            BufferedReader in = new BufferedReader(input);

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<TradesTickerClass> getTickerArray (String payload) throws JSONException {
        ArrayList<TradesTickerClass> arrayList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(payload) ;

        sizeOfPurchases = jsonArray.length();
        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String current = jsonObject.getString("last");
            String last = jsonObject.getString("price");
            String date = jsonObject.getString("date");
            String currency = jsonObject.getString("currency");

            arrayList.add(new TradesTickerClass(i,getChangePercent(current,last)+"%", convertMoney(last), date, currency));
        }

        return arrayList;
    }

    protected String getChangePercent(String currentPrice, String lastPrice){
        Double change  = Double.valueOf(currentPrice) - Double.valueOf(lastPrice);
        Double percent = (change/Double.valueOf(currentPrice)) * 100;
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(percent);
    }

    protected String convertMoney(String number){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String money = nf.format(Double.valueOf(number));
        return money;
    }

}
