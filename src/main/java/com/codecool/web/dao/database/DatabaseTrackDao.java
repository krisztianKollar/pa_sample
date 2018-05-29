package com.codecool.web.dao.database;

import com.codecool.web.dao.EmployeeDao;
import com.codecool.web.dao.TrackDao;
import com.codecool.web.model.Album;
import com.codecool.web.model.Artist;
import com.codecool.web.model.Employee;
import com.codecool.web.model.Track;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTrackDao extends AbstractDao implements TrackDao {


    public DatabaseTrackDao(Connection connection) {
        super(connection);
    }


    @Override
    public List<Track> findAllTrack() throws SQLException {
        String sql = "select t.trackid, ar.name as artist, al.title as album, " +
            "t.name, t.unitprice, g.name as genre from track as t " +
            "join album as al on al.albumid = t.albumid " +
            "join artist as ar on al.artistid = ar.artistid " +
            "join genre as g on t.genreid = g.genreid;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Track> tracks = new ArrayList<>();
            while (resultSet.next()) {
                tracks.add(fetchtrack(resultSet));
            }
            return tracks;
        }
    }

    @Override
    public List<Track> findAllPurchasedTrack() throws SQLException {
        return null;
    }

    @Override
    public void purchaseTrack() throws SQLException {

    }

    @Override
    public void purchaseTracks() throws SQLException {

    }

    @Override
    public void purchaseTracksByAlbum(Album album) throws SQLException {

    }

    @Override
    public void purchaseTracksByAlbums(List<Album> albums) throws SQLException {

    }

    @Override
    public void purchaseTracksByArtist(Artist artist) throws SQLException {

    }

    @Override
    public void purchaseTracksByArtists(List<Artist> artists) throws SQLException {

    }

    @Override
    public void addTrack() throws SQLException {

    }

    @Override
    public void addTrackToAlbum() throws SQLException {

    }

    private Track fetchtrack (ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("trackid");
        String name = resultSet.getString("name");
        int albumId = resultSet.getInt("albumid");
        int mediaTypeId = resultSet.getInt("mediatypeid");
        int genreId = resultSet.getInt("genreid");
        String composer = resultSet.getString("composer");
        int milliSeconds = resultSet.getInt("milliseconds");
        int bytes = resultSet.getInt("bytes");
        float unitPrice = resultSet.getFloat("unitprice");
        return new Track(id, name, albumId, mediaTypeId, genreId, composer, milliSeconds, bytes, unitPrice);
    }

    // not null add: INSERT INTO track (name, mediatypeid, milliseconds, unitprice) VALUES
    // add all: INSERT INTO track (name, albumid, mediatypeid, genreid, composer, milliseconds, bytes, unitprice) VALUES
}
