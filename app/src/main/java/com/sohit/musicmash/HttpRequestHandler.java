package com.sohit.musicmash;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

/**
 * Created by sohit on 10/6/17.
 */

public class HttpRequestHandler extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... urls) {
        String result = "";

        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpResponse response = httpclient.execute(new HttpGet(urls[0]));
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                result = convertStreamToString(instream);
                instream.close();
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return result;
    }

    public static String convertStreamToString(InputStream is) {
        /* Taken from Stackoverflow */

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO: Nothing right now
    }
}
