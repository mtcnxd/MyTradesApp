package com.tcdev.mytrades.TradesClass;

public class TradesBalanceClass {
    String currency = "";
    String amount = "";
    String value = "";

    public TradesBalanceClass (String currency, String amount, String value){
        this.currency = currency;
        this.amount = amount;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAmount() {
        return amount;
    }

    public String getValue() {
        return value;
    }
}
