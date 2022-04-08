package com.tcdev.mytrades.TradesClass;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lecho.lib.hellocharts.model.PointValue;

public class Trades {
    protected String baseUrl = "https://test.fortech.mx";
    protected int sizeOfPurchases = 0;

    public int getSizeOfPurchases(){
        return sizeOfPurchases;
    }

    public String getTradesRequest(String RequestPath, String request)
    {
        try {
            String url = baseUrl + RequestPath + "?request=" + request;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("User-Agent", "Android Trades Application");
            con.setRequestMethod("POST");
            con.setDoOutput(true);

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

            String current = jsonObject.getString("current");
            String last = jsonObject.getString("last");
            String date = jsonObject.getString("date");
            String currency = jsonObject.getString("currency");

            arrayList.add(new TradesTickerClass(i,getChangePercent(current,last)+"%",convertMoney(current), convertMoney(last), date, currency));
        }

        return arrayList;
    }

    public ArrayList<TradesStatisticsClass> getStatisticsArray(String payload){
        ArrayList<TradesStatisticsClass> arrayList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(payload);
            for (int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String key = jsonObject.getString("key");
                String value = jsonObject.getString("value");

                arrayList.add(new TradesStatisticsClass(key, value));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public ArrayList<TradesBalanceClass> getBalanceArray(String payload) {
        ArrayList<TradesBalanceClass> arrayList = new ArrayList<>();

        Log.d("Mensaje", "JSON: " + payload);
        try {
            JSONArray jsonArray = new JSONArray(payload);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String currency = jsonObject.getString("currency");
                String amount = jsonObject.getString("amount");
                String value = jsonObject.getString("value");

                arrayList.add(new TradesBalanceClass(currency.toUpperCase(),amount,convertMoney(value)));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    public List<PointValue> getChartDataBalances(String payload) throws JSONException {
        List<PointValue> values = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(payload);

        for(int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String amount = jsonObject.getString("amount");
            values.add(new PointValue(i, Float.valueOf(amount)));
        }

        return values;
    }

    protected String getChangePercent(String current, String last){
        Double change  = Double.valueOf(current) - Double.valueOf(last);
        Double percent = (change/Double.valueOf(current)) * 100;
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(percent);
    }

    protected String convertMoney(String number){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String money = nf.format(Double.valueOf(number));
        return money;
    }
}
