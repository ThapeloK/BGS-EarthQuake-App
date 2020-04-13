package com.tkhantsi.mpdassignment;
// NAME:Thapelo Khantsi  ///////
// Student Number: S1803437//////////
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class Result extends AppCompatActivity {

        RSSReader rssReader = new RSSReader();
    RSSParser rssParser = new RSSParser();
    AsyncRSSParser asyncRSSParser;


    private ListView ListView_LIST;
    private ArrayAdapter arrayAdapter;

    private String startDate;
    private String endDate;

    //
    private TextView TextView_TEXTVIEW;
//    private ListView ListView_LIST;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

//        Intent intent = getIntent();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        //Extract the data…
        String startDate = pref.getString("start_date", null);
        Log.d("startDate",startDate);

        String endDate = pref.getString("end_date", null);
        Log.d("endDate",endDate);

        ListView_LIST = findViewById(R.id.result_list);

        TextView_TEXTVIEW = findViewById(R.id.TextView_TEXTVIEW);

        asyncRSSParser = new AsyncRSSParser();
        asyncRSSParser.execute();

        ////////////////////////////////////////////////////////////////////////////////////////////////
        ListView_LIST.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get the selected name
                String selectedTitle = (String)parent.getItemAtPosition(position);

                // Look for Selected Title, once found
                // Display the details of the Title in TextView_TEXTVIEW
                for(int i = 0; i < rssParser.earthQuakeFeedList.size();i++)
                {
                    if(selectedTitle.equals(rssParser.earthQuakeFeedList.get(i).getTitle()))
                    {
//                        TextView_TEXTVIEW.setText(rssParser.earthQuakeFeedList.get(i).toString());
                        Intent intent;
                        intent = new Intent(Result.this,single.class);
                        intent.putExtra("quake_data", rssParser.earthQuakeFeedList.get(i).toString());
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }


    private class AsyncRSSParser extends AsyncTask<Void, Void, LinkedList<EarthQuakeFeed>> {

        protected LinkedList<EarthQuakeFeed> doInBackground(Void... nothing) {
            LinkedList<EarthQuakeFeed> earthQuakeFeedList = null;



            // Fetch RSS data from British Geological Services
            rssReader.FetchRSS();

            // Parse the xml of the RSS into a LinkedList of earthQuake Feed
            rssParser.parseRSSString(rssReader.getRssString(), startDate, endDate);

            // Return the LinkedList containing earthQuake Feed
            return rssParser.earthQuakeFeedList;
        }

        // Print the First Element from the earthQuakeFeedList returned by doInBackground()
        protected void onPostExecute(LinkedList<EarthQuakeFeed> earthQuakeFeedList) {
            LinkedList titles = rssParser.titleList;
//            Log.d("start",startDate);
//            Log.d("end",endDate);
//            if(!startDate.equals("") && !endDate.equals("")) {
//                for (int i = 0; i < titles.size(); i++) {
////                System.out.println(titles.get(i));
//                    String[] split= titles.get(i).toString().split(" ");
//                    String dateInTitle = split[split.length-4] + split[split.length-3] + split[split.length-2];
//                    DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//                    try {
//                        Date searchableDate = format.parse(dateInTitle);
//                        Log.d("searchableDate",searchableDate.toString());
//                        Date start = format.parse(startDate);
//                        Log.d("start",start.toString());
//                        Date end = format.parse(endDate);
//                        Log.d("end",end.toString());
//                        if (searchableDate != null) {
//                            if(!(((Date) searchableDate).before(start) || ((Date) searchableDate).after(end))) {
//
//                            }
//                            else {
//                                titles.remove(i);
//                            }
//                        }
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
////            }

            arrayAdapter = new ArrayAdapter(Result.this, android.R.layout.simple_list_item_1, titles);
            ListView_LIST.setAdapter(arrayAdapter);
        }

    }
}
