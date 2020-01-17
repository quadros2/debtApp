package com.example.mydebtapp1;

public class DebtClassToPush {

    private String recipient;
    private String amount;
    private String date;
    private String description;
    private String currency;

    public DebtClassToPush() {
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String setRecipient) {
        recipient = setRecipient;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String setAmount) {
        amount = setAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String setDate) {
        date = setDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String setDescription) {
        description = setDescription;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String setCurrency) {
        currency = setCurrency;
    }
}
