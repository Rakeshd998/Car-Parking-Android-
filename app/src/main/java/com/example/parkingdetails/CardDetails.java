package com.example.parkingdetails;

public class CardDetails {
    private long id;
    private long userId;
    private String bankName;
    private String cardNumber;
    private String expireDate;

    public CardDetails() {
        // Default constructor
    }

    public CardDetails(long userId, String bankName, String cardNumber, String expireDate) {
        this.userId = userId;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
    }

    // Getters and setters for CardDetails
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}

