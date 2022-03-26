package com.tcdev.mytrades.TradesClass;

public class TradesStatisticsClass {
    String keyLeft = "";
    String valueLeft = "";
    String keyRight = "";
    String valueRight = "";

    public TradesStatisticsClass(String keyLeft, String valueLeft, String keyRight, String valueRight){
        this.keyLeft = keyLeft;
        this.valueLeft = valueLeft;
        this.keyRight = keyRight;
        this.valueRight = valueRight;
    }

    public String getKeyLeft() {
        return keyLeft;
    }

    public String getValueLeft() {
        return valueLeft;
    }

    public String getKeyRight() {
        return keyRight;
    }

    public String getValueRight() {
        return valueRight;
    }
}
