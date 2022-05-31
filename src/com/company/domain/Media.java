package com.company.domain;

import java.util.ArrayList;
import java.util.List;

public class Media {

    private int id;
    private String title;
    private String subtitle;
    private String director;
    private String genre;
    private int year;
    private String category;
    private String[] actor;

    public Media(String title, String subtitle, String director, String genre, String[] actor, int year, String category) {
        this.id = (int) (Math.random() * 1000000);
        this.title = title;
        this.subtitle = subtitle;
        this.director = director;
        this.genre = genre;
        this.actor = actor;
        this.year = year;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String[] getActorList() {
        return actor;
    }
    public String getActorString() {
        String actors = "";
        for (String s : actor) {
            actors += s + ", ";
        }
        return actors;
    }

    public void setActor(String[] actor) {
        this.actor = actor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return title + (subtitle != null ? " - " + subtitle : "") + " (" + year + ") " + "Directed by " + director;
    }
}