package com.example.doyouknow.modal;

public class Facts {
    String Content, Category, Time, Date, FactID;

    public Facts() {
    }

    public Facts(String content, String category, String time, String date, String factID) {
        Content = content;
        Category = category;
        Time = time;
        Date = date;
        FactID = factID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getFactID() {
        return FactID;
    }

    public void setFactID(String factID) {
        FactID = factID;
    }
}