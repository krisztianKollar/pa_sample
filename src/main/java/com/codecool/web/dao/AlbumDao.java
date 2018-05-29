package com.codecool.web.dao;

import com.codecool.web.model.Album;

import java.sql.SQLException;
import java.util.List;

public interface AlbumDao {

    List<Album> findAllAlbum() throws SQLException;

    List<Album> findAllPurchasedAlbum() throws SQLException;

    void addAlbum() throws SQLException;

    void addAlbumToArtist() throws SQLException;
}
