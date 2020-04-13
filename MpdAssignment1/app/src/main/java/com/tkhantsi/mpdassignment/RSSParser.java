package com.tkhantsi.mpdassignment;
// NAME:Thapelo Khantsi  ///////
// Student Number: S1803437//////////
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class RSSParser {

    // Linked List containing earthQuake Feed each with its own title, description, coordinates,link and publication date
    LinkedList <EarthQuakeFeed> earthQuakeFeedList = null;
    // A LINKED LIST FOR HOLDING THE TITLES OF earthQuake Feed
    // TO BE USED IN LISTVIEW
    LinkedList<String> titleList = null;


    public LinkedList<EarthQuakeFeed> parseRSSString(String rssString, String start , String end )
    {
        EarthQuakeFeed earthQuakeFeed = null;

        try
        {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( rssString ) );
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                // Found a start tag
                if(eventType == XmlPullParser.START_TAG)
                {
                    // Check which Tag we have
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        earthQuakeFeedList  = new LinkedList<EarthQuakeFeed>();
                        titleList     = new LinkedList<String>();

                        for(int i = 0; i<24; i++)
                        {
                            eventType = xpp.next();
                        }
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        earthQuakeFeed = new EarthQuakeFeed();
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        earthQuakeFeed.setTitle(temp);

                    }
                    else if(xpp.getName().equalsIgnoreCase("category")) {
                        String temp = xpp.nextText();
                        earthQuakeFeed.setCategory(temp);
                     }
                    else if(xpp.getName().equalsIgnoreCase("link")) {
                        String temp = xpp.nextText();
                        earthQuakeFeed.setLink(temp);
                    }
                    else if(xpp.getName().equalsIgnoreCase("geo:lat")) {
                        String temp = xpp.nextText();
                        earthQuakeFeed.setCoordinatesLat(temp);
                    }
                    else if(xpp.getName().equalsIgnoreCase("geo:long")) {
                        String temp = xpp.nextText();
                        earthQuakeFeed.setCoordinatesLong(temp);
                    }
                    else
                        // Check which Tag we have
                        if (xpp.getName().equalsIgnoreCase("description"))
                        {
                            // Now just get the associated text
                            String temp = xpp.nextText();
                            earthQuakeFeed.setDescription(temp);
                        }
                        else
                            // Check which Tag we have
                            if (xpp.getName().equalsIgnoreCase("pubDate"))
                            {
                                // Now just get the associated text
                                String temp = xpp.nextText();
                                if (earthQuakeFeed != null) {
                                    earthQuakeFeed.setPubDate(temp);
                                }
                            }
                }
                else
                if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Context applicationContext = MainActivity.getContextOfApplication();
                        SharedPreferences pref = applicationContext.getSharedPreferences("MyPref", 0);

                        //Extract the data…
                        String startDatePref = pref.getString("start_date", null);

                        String endDatePref = pref.getString("end_date", null);

//                        if(startDatePref.equals("") || endDatePref.equals("")) {
                            earthQuakeFeedList.add(earthQuakeFeed);
                            titleList.add(earthQuakeFeed.getTitle());
//                        }
//                        else {
//                            try {
//                                    DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
//                                    Date searchableDate = format.parse(earthQuakeFeed.getPubDate());
//                                    Date startDate = format.parse(startDatePref);
//                                    Date endDate = format.parse(endDatePref);
//                                    if(!(((Date) searchableDate).before(startDate) || ((Date) searchableDate).after(endDate))) {
//                                        earthQuakeFeedList.add(earthQuakeFeed);
//                                        titleList.add(earthQuakeFeed.getTitle());
//                                    }
//                                    else {
////                                        titles.remove(i);
//                                    }
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//                        }
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = earthQuakeFeedList.size();
                    }
                }

                // Get the next event
                eventType = xpp.next();
            }

        }

        catch (XmlPullParserException e)
        {
            System.out.println("Parsing Error "+e.toString());
        }

        catch (IOException e)
        {
            System.out.println("Parsing Error "+e.toString());
        }
        return earthQuakeFeedList;
    }

    // THE END
}
