package com.tcdev.mytrades.TradesClass;

public class TradesTickerClass {
    protected String id = "";
    protected String book = "";
    protected String price = "";
    protected String date = "";
    protected String currency = "";

    public TradesTickerClass(String id, String book, String price, String date, String currency){
        this.id = id;
        this.book = book;
        this.price = price;
        this.date = date;
        this.currency = currency;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getId() {
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
