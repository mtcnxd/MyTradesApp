package com.tcdev.mytrades.TradesClass;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Trades {
    protected String baseUrl = "https://test.fortech.mx";

    public String getTradesRequest(String RequestPath)
    {
        try {
            String url = baseUrl + RequestPath;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("User-Agent", "Bitso Java Application");
            con.setRequestMethod("GET");

            InputStreamReader input = new InputStreamReader(con.getInputStream());
            BufferedReader in = new BufferedReader(input);

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String result = response.toString();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<TradesTickerClass> getTickerArray (String payload)
    {
        ArrayList<TradesTickerClass> arrayList = new ArrayList<>();

        arrayList.add(new TradesTickerClass("","Book","Price","Date","Currency"));

        return arrayList;
    }

}
