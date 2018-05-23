package com.codecool.web.model;

public class Employee extends Person {

    private String title;
    private int numOfRepresentedCustomers;

    public Employee(int id, String firstName, String lastName, String address,
                    String city, String country, String postalCode,
                    String email, String title, int numOfRepresentedCustomers) {
        super(id, firstName, lastName, address, city, country, postalCode, email);
        this.title = title;
        this.numOfRepresentedCustomers = numOfRepresentedCustomers;

    }

    public String getTitle() {
        return title;
    }

    public int getNumOfRepresentedCustomers() {
        return numOfRepresentedCustomers;
    }

}
