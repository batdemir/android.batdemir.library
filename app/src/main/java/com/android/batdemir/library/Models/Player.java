package com.android.batdemir.library.Models;

public class Player {

    private String Name;
    private String Nationality;
    private String Club;
    private Integer Rating;
    private Integer Age;

    public Player() {
    }

    public Player(String name, String nationality, String club, Integer rating, Integer age) {
        Name = name;
        Nationality = nationality;
        Club = club;
        Rating = rating;
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getClub() {
        return Club;
    }

    public void setClub(String club) {
        Club = club;
    }

    public Integer getRating() {
        return Rating;
    }

    public void setRating(Integer rating) {
        Rating = rating;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }
}
