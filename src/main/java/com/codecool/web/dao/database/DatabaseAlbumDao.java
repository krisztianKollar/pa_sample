package com.codecool.web.dao.database;

import com.codecool.web.dao.AlbumDao;
import com.codecool.web.dao.TrackDao;
import com.codecool.web.model.Album;
import com.codecool.web.model.Artist;
import com.codecool.web.model.Track;

import java.sql.*;
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
            "sum(t.unitprice) as totalprice " +
            "from album as al " +
            "join artist as ar on al.artistid = ar.artistid " +
            "join track as t on al.albumid = t.albumid " +
            "group by al.albumid, ar.name, al.title " +
            "order by ar.name, al.title";
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
    public void addAlbumToArtist(String title, int artistId) throws SQLException {
        String sql = "INSERT INTO album (title, artistid) VALUES (? , ?);";
        System.out.println(artistId);
        System.out.println(title);
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setInt(2, artistId);
            executeInsert(ps);
        }
    }





    private Album fetchalbum(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("albumid");
        String artistName = resultSet.getString("name");
        String title = resultSet.getString("title");
        int numOfTracks = resultSet.getInt("number_of_tracks");
        float totalPrice = resultSet.getFloat("totalprice");
        return new Album(id, artistName, title, numOfTracks, totalPrice);
    }
}
