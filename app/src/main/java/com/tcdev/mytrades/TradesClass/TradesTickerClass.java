package com.tcdev.mytrades.TradesClass;

public class TradesTickerClass {
    protected int id;
    protected String book;
    protected String current;
    protected String last;
    protected String date;
    protected String currency;

    public TradesTickerClass(int id, String book, String current, String last, String date, String currency){
        this.id = id;
        this.book = book;
        this.current = current;
        this.last = last;
        this.date = date;
        this.currency = currency;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getBook() {
        return book;
    }

    public String getCurrent() {
        return current;
    }

    public String getLast() {
        return last;
    }

    public String getDate() {
        return date;
    }

    public String getCurrency() {
        return currency;
    }
}
