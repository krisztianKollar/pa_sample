package com.codecool.web.model;

public class User extends Person {

    private String company;
    private String salesReprContactInf;
    private int numOfPurchases;

    public User(int id, String firstName, String lastName, String email, String fullAddress,
                String company, String salesReprContactInf, int numOfPurchases) {
        super(id, firstName, lastName, email, fullAddress);
        this.company = company;
        this.salesReprContactInf = salesReprContactInf;
        this.numOfPurchases = numOfPurchases;
    }

    public User(int id, String email) {
        super(id, email);
    }


    public String getCompany() {
        return company;
    }

    public String getSalesReprContactInf() {
        return salesReprContactInf;
    }

    public int getNumOfPurchases() {
        return numOfPurchases;
    }
}
