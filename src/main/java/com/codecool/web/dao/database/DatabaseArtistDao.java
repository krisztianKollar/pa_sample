package com.codecool.web.dao.database;

import com.codecool.web.dao.ArtistDao;
import com.codecool.web.dao.TrackDao;
import com.codecool.web.model.Album;
import com.codecool.web.model.Artist;
import com.codecool.web.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseArtistDao extends AbstractDao implements ArtistDao {


    public DatabaseArtistDao(Connection connection) {
        super(connection);
    }


    @Override
    public List<Artist> findAllArtist() throws SQLException {
        String sql = "SELECT ar.artistid, ar.name, " +
            "(select count(albumid) as number_of_albums from album where artistid=ar.artistid), " +
            "count(t.trackid) as number_of_tracks, " +
            "sum(t.unitprice) as totalprice " +
            "from artist as ar " +
            "join album as al on al.artistid = ar.artistid " +
            "join track as t on al.albumid = t.albumid " +
            "group by ar.artistid, ar.name " +
            "order by ar.name";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Artist> artists = new ArrayList<>();
            while (resultSet.next()) {
                artists.add(fetchartist(resultSet));
            }
            return artists;
        }
    }

    @Override
    public List<Artist> findAllPurchasedArtist() throws SQLException {
        return null;
    }

    @Override
    public void addArtist() throws SQLException {

    }

    @Override
    public int getArtistIdByName(String name) throws SQLException {
        String sql = "SELECT artistid from artist where name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt("artistid");
            }
            return -1;
        }
    }


    private Artist fetchartist(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("artistid");
        String name = resultSet.getString("name");
        int totalNumOfAlbums = resultSet.getInt("number_of_albums");
        int totalNumOfTracks = resultSet.getInt("number_of_tracks");
        float totalPrice = resultSet.getFloat("totalprice");
        return new Artist(id, name, totalNumOfAlbums, totalNumOfTracks, totalPrice);
    }
}
