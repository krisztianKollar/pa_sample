package com.codecool.web.model;

public class Customer extends Person {

    private String company;
    private String salesReprContactInf;
    private int numOfPurchases;

    public Customer(int id, String firstName, String lastName, String address,
                    String city, String country, String postalCode, String email,
                    String company, String salesReprContactInf, int numOfPurchases) {
        super(id, firstName, lastName, address, city, country, postalCode, email);
        this.company = company;
        this.salesReprContactInf = salesReprContactInf;
        this.numOfPurchases = numOfPurchases;
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
