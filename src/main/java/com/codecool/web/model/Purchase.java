package com.codecool.web.model;

public class Purchase {

    private int invoiceId;
    private int numberOfTracks;
    private int totalPrice;
    private String artistName;
    private String albumTitle;
    private String trackTitle;
    private int unitPrice;
    private String genre;

    public Purchase(int invoiceId, int numberOfTracks, int totalPrice) {
        this.invoiceId = invoiceId;
        this.numberOfTracks = numberOfTracks;
        this.totalPrice = totalPrice;
    }

    public Purchase(int invoiceId, String artistName, String albumTitle, String trackTitle, int unitPrice, String genre) {
        this.invoiceId = invoiceId;
        this.artistName = artistName;
        this.albumTitle = albumTitle;
        this.trackTitle = trackTitle;
        this.unitPrice = unitPrice;
        this.genre = genre;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public String getGenre() {
        return genre;
    }
}
