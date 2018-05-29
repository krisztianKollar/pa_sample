package com.codecool.web.dao;

import com.codecool.web.model.Album;
import com.codecool.web.model.Artist;
import com.codecool.web.model.Track;

import java.sql.SQLException;
import java.util.List;

public interface ArtistDao {

    List<Artist> findAllArtist() throws SQLException;

    List<Artist> findAllPurchasedArtist() throws SQLException;

    void addArtist() throws SQLException;

}
