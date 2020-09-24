package com.example.hackillinoischallenge;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;

public class Event {

    private String id, name, description, sponsor, eventType;
    private ArrayList<String> locations;
    private Date startDate, endDate;

    public Event(String id, String name, String description, String sponsor, String eventType, JSONArray locations, long start, long end) throws JSONException {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sponsor = sponsor;
        this.eventType = eventType;

        this.locations = new ArrayList<>();
        for (int i = 0; i < locations.length(); i++) {
            this.locations.add(locations.get(i).toString());
        }

        startDate = new Date(start);
        endDate = new Date(end);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
