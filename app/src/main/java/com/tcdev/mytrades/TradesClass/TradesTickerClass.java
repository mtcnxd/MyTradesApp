package com.tcdev.mytrades.TradesClass;

public class TradesTickerClass {
    protected int id;
    protected String percent;
    protected String current;
    protected String last;
    protected String date;
    protected String currency;

    public TradesTickerClass(int id, String percent, String current, String last, String date, String currency){
        this.id = id;
        this.percent = percent;
        this.current = current;
        this.last = last;
        this.date = date;
        this.currency = currency;
    }

    public String getPercent() {
        return percent;
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
