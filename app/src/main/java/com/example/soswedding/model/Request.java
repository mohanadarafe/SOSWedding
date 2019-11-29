package com.example.soswedding.model;

import java.io.Serializable;

public class Request implements Serializable {

    private String title;
    private String description;
    private String type;
    private String address;
    private long id;
    private double budget;
    private String uID;
    private String status;

    public Request(long id, String title, String description, String type, String address, double budget, String uID, String status){
        this.id          = id;
        this.title       = title;
        this.description = description;
        this.type        = type;
        this.budget      = budget;
        this.address     = address;
        this.uID         = uID;
        this.status      = status;
    }
    public Request(String title, String description, String type, String address, double budget, String uID, String status){
        this.title       = title;
        this.description = description;
        this.type        = type;
        this.budget      = budget;
        this.address     = address;
        this.uID         = uID;
        this.status      = status;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public double getBudget() {
        return budget;
    }
    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getuID() {
        return uID;
    }
    public void setuID(String uID) {
        this.uID = uID;
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


    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}