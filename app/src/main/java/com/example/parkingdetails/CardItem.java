package com.example.parkingdetails;

public class CardItem {
    public String name;
    public String bname;
    public String cn;
    public String ex;
    public String ph;

    public CardItem(String name,String bname,String cn,String ex,String ph)
    {
        this.name = name;
        this.bname=bname;
        this.cn=cn;
        this.ex=ex;
        this.ph=ph;
    }

    public String getName() {
        return name;
    }
}