package com.tcdev.mytrades.BitsoClass;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Bitso {
    protected String bitsoKey = "BLpkbWFduP";
    protected String bitsoSecret = "34cfc69a8845fbe7832a8845895c956a";
    protected String baseUrl = "https://api.bitso.com";
    protected String jsonPayload = "";

    public String getBitsoRequest(String RequestPath){
        long nonce = System.currentTimeMillis();
        String HTTPMethod = "GET";
        String JSONPayload = "";

        try {
            String message = nonce + HTTPMethod + RequestPath + JSONPayload;
            String signature = "";
            byte[] secretBytes = bitsoSecret.getBytes();
            SecretKeySpec localMac = new SecretKeySpec(secretBytes, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(localMac);

            byte[] arrayOfByte = mac.doFinal(message.getBytes());
            BigInteger localBigInteger = new BigInteger(1, arrayOfByte);
            signature = String.format("%0" + (arrayOfByte.length << 1) + "x", new Object[] { localBigInteger });

            String authHeader = String.format("Bitso %s:%s:%s", bitsoKey, nonce, signature);
            String url = baseUrl + RequestPath;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("User-Agent", "Bitso Java Application");
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", authHeader);

            InputStreamReader input = new InputStreamReader(con.getInputStream());
            BufferedReader in = new BufferedReader(input);

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);

                Log.d("Mensaje", "getBitsoRequest: " + in);
            }
            in.close();

            String result = GetJSONPayload(response.toString());
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String GetJSONPayload(String jsonResponse) {
        try {
            JSONObject o = new JSONObject(jsonResponse);
            if (o.has("payload")) {
                jsonPayload = o.get("payload").toString();
                return o.get("payload").toString();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
