package com.android.batdemir.library.models;

public class Player {

    private String name;
    private String nationality;
    private String club;
    private Integer rating;
    private Integer age;

    public Player() {
    }

    public Player(String name, String nationality, String club, Integer rating, Integer age) {
        this.name = name;
        this.nationality = nationality;
        this.club = club;
        this.rating = rating;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
