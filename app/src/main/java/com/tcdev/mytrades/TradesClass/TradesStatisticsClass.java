package com.tcdev.mytrades.TradesClass;

public class TradesStatisticsClass {
    String key = "";
    String value = "";

    public TradesStatisticsClass(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
