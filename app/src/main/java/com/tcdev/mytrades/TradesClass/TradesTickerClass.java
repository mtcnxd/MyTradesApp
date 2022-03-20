package com.tcdev.mytrades.TradesClass;

public class TradesTickerClass {
    protected int id;
    protected String book;
    protected String price;
    protected String date;
    protected String currency;

    public TradesTickerClass(int id, String book, String price, String date, String currency){
        this.id = id;
        this.book = book;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getCurrency() {
        return currency;
    }
}
