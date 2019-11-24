package com.example.soswedding.model;

import java.io.Serializable;

public class Request implements Serializable {

    private String title;
    private String description;
    private String type;
    private String address;

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    private String budget;

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

    private String uID;

    public Request(String title, String description, String type, String address, String budget){
        this.title = title;
        this.description = description;
        this.type = type;
        this.budget = budget;
        this.address = address;
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

}