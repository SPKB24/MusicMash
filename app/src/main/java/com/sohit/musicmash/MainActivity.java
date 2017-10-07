package com.sohit.musicmash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For the main search button
        Button btn = (Button) findViewById(R.id.search_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchByQuery();
            }
        });

        // The following onClickListeners are for all of the theme buttons
        btn = (Button) findViewById(R.id.btn_study);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToListViewPage("dubstep");
            }
        });
        btn = (Button) findViewById(R.id.btn_workout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToListViewPage("workout music");
            }
        });
        btn = (Button) findViewById(R.id.btn_sleep);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToListViewPage("study music");
            }
        });
        btn = (Button) findViewById(R.id.btn_dubstep);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToListViewPage("sleep music");
            }
        });
    }

    // If user input, bring them to a page where a list of youtube videos is displayed
    private void searchByQuery() {

        String query = getSearchQuery();

        if (query.isEmpty()) {
            if (toast != null) { toast.cancel(); }
            toast = Toast.makeText(this, "Empty input", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            goToListViewPage(query);
        }
    }

    // Basically, get text from editText view, and return it as a string
    private String getSearchQuery() {
        EditText editText = (EditText) findViewById(R.id.search_query_input);
        System.out.println("INPUT IS: '" + editText.getText().toString() + "'");
        return editText.getText().toString();
    }

    // For themes, take theme type, and start intent to another page
    private void goToListViewPage(String pageType) {
        Intent intent = new Intent(MainActivity.this, YoutubeVideoList.class);
        intent.putExtra("search_query", pageType);
        startActivity(intent);
    }
}
