package com.codecool.web.service.simple;

import com.codecool.web.dao.AlbumDao;
import com.codecool.web.dao.ArtistDao;
import com.codecool.web.dao.TrackDao;
import com.codecool.web.model.Album;
import com.codecool.web.model.Track;

import java.sql.SQLException;
import java.util.List;

public final class SimpleAlbumService {

    private final AlbumDao albumDao;
    private final ArtistDao artistDao;

    public SimpleAlbumService(AlbumDao albumDao, ArtistDao artistDao) {
        this.albumDao = albumDao;
        this.artistDao = artistDao;
    }

    public void addAlbumByArtistName(String albumName, String artistName) throws SQLException {
        int artistId = artistDao.getArtistIdByName(artistName);
        albumDao.addAlbumToArtist(albumName, artistId);
    }

    public List<Album> getAlbums() throws SQLException{
        return albumDao.findAllAlbum();
    }

}
