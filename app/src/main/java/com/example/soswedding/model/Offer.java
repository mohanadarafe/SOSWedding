package com.example.soswedding.model;

public class Offer {

    private String title;
    private String description;
    private String type; // not sure for the type
    private String uID;
    private double budget;

    public Offer(String title, String description, String type, double budget) {

        this.title = title;
        this.description = description;
        this.type = type;
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
}
