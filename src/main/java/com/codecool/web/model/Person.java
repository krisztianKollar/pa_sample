package com.codecool.web.model;

public abstract class Person extends AbstractModel {

    protected String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String email;


    public Person(int id, String firstName, String lastName, String address,
                  String city, String country, String postalCode, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEmail() {
        return email;
    }
}


