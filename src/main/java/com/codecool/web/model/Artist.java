package com.codecool.web.model;

import java.util.List;

public class Artist extends AbstractModel{

    private String name;
    private int totalNumOfAlbums;
    private int totalNumOfTracks;
    private int totalPrice;

    public Artist(int id, String name, int totalNumOfAlbums, int totalNumOfTracks, int totalPrice) {
        super(id);
        this.name = name;
        this.totalNumOfAlbums = totalNumOfAlbums;
        this.totalNumOfTracks = totalNumOfTracks;
        this.totalPrice = totalPrice;
    }

    public Artist(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getTotalNumOfAlbums() {
        return totalNumOfAlbums;
    }

    public int getTotalNumOfTracks() {
        return totalNumOfTracks;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    // INSERT INTO artist (name) VALUES
}
