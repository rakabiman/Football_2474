package com.example.football_2474.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MatchResponse {
    @SerializedName("events")
    private ArrayList<Match> events;

    @SerializedName("total_events")
    private int total_events;

    public ArrayList<Match> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Match> events) {
        this.events = events;
    }

    public int getTotal_events() {
        return total_events;
    }

    public void setTotal_events(int total_events) {
        this.total_events = total_events;
    }
}
