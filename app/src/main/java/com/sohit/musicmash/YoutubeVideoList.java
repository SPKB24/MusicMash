package com.sohit.musicmash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

public class YoutubeVideoList extends AppCompatActivity {

    protected String search_query = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_options_page);

        // Get intent data - search query
        Bundle extras = getIntent().getExtras();
        search_query = extras.getString("search_query");

        // Get Youtube data based on search query
        JSONObject json = null;
        try {
            HttpRequestHandler handler = new HttpRequestHandler();
            String response = handler.execute(YouTubeVideoHandler.createYoutubeUrl(search_query, 10)).get();
            json = YouTubeVideoHandler.stringToJson(response);
        } catch (Exception e) {
            Toast.makeText(this, "You Suck", Toast.LENGTH_LONG).show();
        }

        // Display the items in a list format on the screen
        ArrayList<CustomItem> items = YouTubeVideoHandler.parseJson(json);

        ListAdapter adapter = new ListAdapter(this, items, R.color.colorPrimary);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
