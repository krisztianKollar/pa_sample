package com.codecool.web.dao;

import com.codecool.web.model.Album;
import com.codecool.web.model.Artist;
import com.codecool.web.model.Track;

import java.sql.SQLException;
import java.util.List;

public interface TrackDao {

    List<Track> findAllTrack() throws SQLException;

    List<Track> findAllPurchasedTrack() throws SQLException;

    void purchaseTrack() throws SQLException;

    void purchaseTracks() throws SQLException;

    void purchaseTracksByAlbum(Album album) throws SQLException;

    void purchaseTracksByAlbums(List<Album> albums) throws SQLException;

    void purchaseTracksByArtist(Artist artist) throws SQLException;

    void purchaseTracksByArtists(List<Artist> artists) throws SQLException;

    void addTrack() throws SQLException;

    void addTrackToAlbum() throws SQLException;

}
