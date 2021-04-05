package com.example.manageapp.activities;

import java.sql.Date;

public class CustomerModel {
    private int customerID;
    private String name;
    private String category;
    private int amount;
    private int day;
    private int month;
    private int year;

    public CustomerModel(int customerID, String name, String category, int amount, int day, int month, int year) {
        this.customerID = customerID;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public CustomerModel(int i, String s, String toString, int string, String s1) {
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
