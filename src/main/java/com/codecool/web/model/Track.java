package com.codecool.web.model;

public class Track extends AbstractModel {

    private String name;
    private int albumId;
    private int mediaTypeId;
    private int genreId;
    private String composer;
    private int milliSeconds;
    private int bytes;
    private float unitPrice;

    public Track(int id, String name, int albumId, int mediaTypeId,
                 int genreId, String composer, int milliSeconds, int bytes, float unitPrice) {
        super(id);
        this.name = name;
        this.albumId = albumId;
        this.mediaTypeId = mediaTypeId;
        this.genreId = genreId;
        this.composer = composer;
        this.milliSeconds = milliSeconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }

    public Track(int id, String name, int mediaTypeId, int milliSeconds, float unitPrice) {
        super(id);
        this.name = name;
        this.mediaTypeId = mediaTypeId;
        this.milliSeconds = milliSeconds;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getMediaTypeId() {
        return mediaTypeId;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getComposer() {
        return composer;
    }

    public int getMilliSeconds() {
        return milliSeconds;
    }

    public int getBytes() {
        return bytes;
    }

    public float getUnitPrice() {
        return unitPrice;
    }
}
