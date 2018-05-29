package com.codecool.web.model;

public abstract class Person extends AbstractModel {

    protected String firstName;
    private String lastName;
    private String email;
    private String fullAddress;


    public Person(int id, String firstName, String lastName, String email, String fullAddress) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.fullAddress = fullAddress;
    }

    public Person(int id, String email) {
        super(id);
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFullAddress() {
        return fullAddress;
    }
}


