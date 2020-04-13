package com.tkhantsi.mpdassignment;
// NAME:Thapelo Khantsi  ///////
// Student Number: S1803437//////////
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class single extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String quakeData = intent.getStringExtra("quake_data");

        if (quakeData != null) {
            String[] split = quakeData.split("--");
            TextView mainTitle = findViewById(R.id.maintitle);
            mainTitle.setText(split[0]);

            TextView datetime = findViewById(R.id.datetime);
            datetime.setText(split[1]);

            TextView fulldetails = findViewById(R.id.fulldetails);
            fulldetails.setText(split[2]);

            TextView link = findViewById(R.id.link);
            link.setText(split[3]);

            TextView magnitude = findViewById(R.id.magnitude);
            magnitude.setText(split[5]);

            TextView depth = findViewById(R.id.depth);
            depth.setText(split[6]);

            TextView location = findViewById(R.id.location);
            location.setText(split[7]);

            TextView category = findViewById(R.id.category);
            category.setText(split[8]);
        }




    }

}
