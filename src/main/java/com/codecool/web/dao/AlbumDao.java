package com.codecool.web.dao;

import com.codecool.web.model.Album;

import java.sql.SQLException;
import java.util.List;

public interface AlbumDao {

    List<Album> findAllAlbum() throws SQLException;

    List<Album> findAllPurchasedAlbum(int customerId) throws SQLException;

    void addAlbum() throws SQLException;

    void addAlbumToArtist(String title, int artistId) throws SQLException;
}
