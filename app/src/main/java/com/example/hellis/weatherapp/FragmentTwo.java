package com.example.hellis.weatherapp;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class FragmentTwo extends Fragment {



    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        DefaultHttpClient client = new DefaultHttpClient();
        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20item." +
                "condition%20from%20weather.forecast%20where%20woeid%20%3D%2022180&" +
                "format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        HttpPost httpGet = new HttpPost(url );
        InputStream inputStream = null;
        String result = null;
        try {
            HttpResponse execute = client.execute(httpGet);
            InputStream content = execute.getEntity().getContent();

            BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
            String s = "";
            while ((s = buffer.readLine()) != null) {
                result += s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("", result);
        try {
            Log.i("", "parsing json");
            JSONObject weather = new JSONObject(result).getJSONObject("query").
                    getJSONObject("results").getJSONObject("channel").
                    getJSONObject("item").getJSONObject("condition");
            Log.i("", "item fetched");
            TextView textView  = (TextView)view.findViewById(R.id.textView2);
            textView.setText(weather.getString("temp"));
            TextView textView2  = (TextView)view.findViewById(R.id.textView3);
            textView2.setText(weather.getString("text"));

            Log.i("", "written to screen");
        } catch (Exception e) {;
            e.printStackTrace();
        }


        //textViewF2 = (TextView) view.findViewById(R.id.textView);
        return view;

    }

}