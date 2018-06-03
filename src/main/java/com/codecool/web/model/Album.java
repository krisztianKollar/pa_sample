package com.codecool.web.model;

public class Album extends AbstractModel{

    private String artistName;
    private String title;
    private int artistId;
    private int numOfTracks;
    private float totalPrice;

    public Album(int id, String artistName, String title, int artistId, int numOfTracks, float totalPrice) {
        super(id);
        this.artistName = artistName;
        this.title = title;
        this.artistId = artistId;
        this.numOfTracks = numOfTracks;
        this.totalPrice = totalPrice;
    }

    public Album(int id, String artistName, String title, int numOfTracks, float totalPrice) {
        super(id);
        this.artistName = artistName;
        this.title = title;
        this.numOfTracks = numOfTracks;
        this.totalPrice = totalPrice;
    }

    public Album(int id, String artistName, String title, float totalPrice) {
        super(id);
        this.artistName = artistName;
        this.title = title;
        this.totalPrice = totalPrice;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getTitle() {
        return title;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getNumOfTracks() {
        return numOfTracks;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
