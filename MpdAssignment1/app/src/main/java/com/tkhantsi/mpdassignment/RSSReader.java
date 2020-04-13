package com.tkhantsi.mpdassignment;
// NAME:Thapelo Khantsi  ///////
// Student Number: S1803437//////////
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RSSReader {
    private String urlString = "https://quakes.bgs.ac.uk/feeds/WorldSeismology.xml";
    private String rssString = "";

    public String getRssString()
    {
        return rssString;
    }

    public void FetchRSS()
    {

        try
        {
            URL url = new URL(urlString);
            URLConnection conn = (URLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;

            while ((inputLine = in .readLine()) != null)
            {
                rssString += inputLine;
            }
            in .close();

        }

        catch (Exception e)

        {
            System.out.println(e.toString());
        }


    }

}
