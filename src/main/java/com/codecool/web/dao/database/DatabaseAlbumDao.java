package com.codecool.web.dao.database;

import com.codecool.web.dao.AlbumDao;
import com.codecool.web.dao.TrackDao;
import com.codecool.web.model.Album;
import com.codecool.web.model.Artist;
import com.codecool.web.model.Track;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAlbumDao extends AbstractDao implements AlbumDao {


    public DatabaseAlbumDao(Connection connection) {
        super(connection);
    }


    @Override
    public List<Album> findAllAlbum() throws SQLException {
        String sql = "SELECT al.albumid, ar.name, al.title, " +
            "count(t.trackid) as number_of_tracks, " +
            "sum(t.unitprice) " +
            "from album as al " +
            "join artist as ar on al.artistid = ar.artistid " +
            "join track as t on al.albumid = t.albumid " +
            "group by al.albumid, ar.name, al.title";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Album> albums = new ArrayList<>();
            while (resultSet.next()) {
                albums.add(fetchalbum(resultSet));
            }
            return albums;
        }
    }

    @Override
    public List<Album> findAllPurchasedAlbum() throws SQLException {
        return null;
    }

    @Override
    public void addAlbum() throws SQLException {

    }

    @Override
    public void addAlbumToArtist() throws SQLException {

    }



    private Album fetchalbum(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("albumid");
        String artistName = resultSet.getString("name");
        String title = resultSet.getString("title");
        int artistId = resultSet.getInt("artistid");
        int numOfTracks = resultSet.getInt("number_of_tracks");
        float totalPrice = resultSet.getFloat("totalprice");
        return new Album(id, artistName, title, artistId, numOfTracks, totalPrice);
    }
}
