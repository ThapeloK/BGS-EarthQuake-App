package com.tkhantsi.mpdassignment;
// NAME:Thapelo Khantsi  ///////
// Student Number: S1803437//////////
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

public static Context contextOfApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contextOfApplication = getApplicationContext();

    }

    public void onButtonClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Result.class);
        myIntent.putExtra("start_date", "");
        myIntent.putExtra("end_date", "");
        startActivity(myIntent);
    }

    public void onSearchButtonClick(View v){
        Intent myIntent = new Intent(this,   Result.class);
        EditText start = findViewById(R.id.startdate);
        String startDate = start.getText().toString();

        EditText end = findViewById(R.id.enddate);
        String enddate = end.getText().toString();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("start_date", startDate);
        editor.putString("end_date", enddate);
        editor.commit();
//        Log.d("start",startDate);
//        Log.d("end",enddate);
        startActivity(myIntent);
    }

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

}
