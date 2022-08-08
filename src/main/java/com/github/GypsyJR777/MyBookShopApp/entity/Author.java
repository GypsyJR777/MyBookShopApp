package com.github.GypsyJR777.MyBookShopApp.entity;

public class Author {
    private Integer id;
    private String firstName;
    private String lastName;
    private String firstLetter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        setFirstLetter(lastName.substring(0, 1));
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstLetter='" + firstLetter + '\'' +
                '}';
    }
}
