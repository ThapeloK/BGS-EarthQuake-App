package com.tkhantsi.mpdassignment;
// NAME:Thapelo Khantsi  ///////
// Student Number: S1803437//////////
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EarthQuakeFeed {

    private String title;
    private String pubDate;
    private String description;
    private String link;
    private String coordinatesLat;

    public String getCoordinatesLong() {
        return coordinatesLong;
    }

    public void setCoordinatesLong(String coordinatesLong) {
        this.coordinatesLong = coordinatesLong;
    }

    private String coordinatesLong;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getMagnitute() {
        return magnitute;
    }

    public void setMagnitute(String magnitute) {
        this.magnitute = magnitute;
    }

    public Date getSearchableDate() {
        return searchableDate;
    }

    public void setSearchableDate(Date searchableDate) {
        this.searchableDate = searchableDate;
    }

    private String location;
    private String depth;
    private String magnitute;
    private Date searchableDate;
    private String category;


  //Default constructor
    public EarthQuakeFeed()
    {
        title = "";
        pubDate = "";
        description = "";
        link = "";
        coordinatesLat = "";
        coordinatesLong = "";
    }

    //GETTERS AND SETTERS FOR PRIVATE VARIABLES//

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title=title;
    }

    public String getCategory()
    {
        return this.category;
    }

    public void setCategory(String category)
    {
        this.category=category;
    }

    public String getPubDate()
    {
        return this.pubDate;
    }

    public void setPubDate(String pubDate)
    {

        this.pubDate=pubDate;
//        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//        try {
//            this.searchableDate = format.parse(pubDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {

        this.description=description;
        String[] split = description.split(" ; ");
        this.location = split[1];
        this.depth = split[3];
        this.magnitute = split[4];
    }

    public String getLink()
    {
        return this.link;
    }

    public void setLink(String link)
    {
        this.link=link;
    }

    public String getCoordinatesLat()
    {
        return this.coordinatesLat;
    }

    public void setCoordinatesLat(String coordinates)
    {
        this.coordinatesLat=coordinates;
    }

    public String toString()
    {
        String earthQuakeFeed;
        earthQuakeFeed = title + "--" + pubDate + "--" + description + "--" + link + "--" + coordinatesLat + "--" + magnitute + "--" + depth + "--" + location + "--" + category + "--" + coordinatesLong;

        return earthQuakeFeed;
    }

    public EarthQuakeFeed getall () {
        return this;
    }

}
