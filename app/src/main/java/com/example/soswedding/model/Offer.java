package com.example.soswedding.model;

import java.io.Serializable;

public class Offer implements Serializable {

    private String title;
    private String status;
    private String message;
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private long id;
    private long requestId;
    private double amount;

    public Offer(String title, String status, String message, String companyName, long requestId, double amount) {
        this.title = title;
        this.status = status;
        this.message = message;
        this.companyName = companyName;
        this.requestId = requestId;
        this.amount = amount;
    }

    public Offer(String title, String status, String message, String companyName, long id, long requestId, double amount) {
        this.title = title;
        this.status = status;
        this.message = message;
        this.companyName = companyName;
        this.id = id;
        this.requestId = requestId;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
