package com.example.doyouknow.modal;

public class Blog {
    String Blog, BlogID, Category, Date, Heading, Time, writer;

    public Blog() {
    }

    public Blog(String blog, String blogID, String category, String date, String heading, String time, String writer) {
        Blog = blog;
        BlogID = blogID;
        Category = category;
        Date = date;
        Heading = heading;
        Time = time;
        this.writer = writer;
    }

    public String getBlog() {
        return Blog;
    }

    public void setBlog(String blog) {
        Blog = blog;
    }

    public String getBlogID() {
        return BlogID;
    }

    public void setBlogID(String blogID) {
        BlogID = blogID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}