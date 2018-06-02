package com.codecool.web.dao.database;

import com.codecool.web.dao.PurchaseDao;
import com.codecool.web.dao.TrackDao;
import com.codecool.web.model.Album;
import com.codecool.web.model.Artist;
import com.codecool.web.model.Purchase;
import com.codecool.web.model.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasePurchaseDao extends AbstractDao implements PurchaseDao {

    public DatabasePurchaseDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Purchase> purchaseHistoryByUser(String customerId) throws SQLException {
        String sql = "select i.invoiceid, count(il.trackid ) as number_of_tracks, " +
            "sum(il.unitprice * il.quantity) as total_price from invoice as i " +
            "join invoiceline as il on i.invoiceid = il. invoiceid " +
            "where customerid = ? " +
            "group by i.invoiceid " +
            "order by i.invoiceid;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, customerId);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            List<Purchase> purchases = new ArrayList<>();
            while (rs.next()) {
                purchases.add(fetchpurchase(rs));
            }
            return purchases;
        }
    }

    @Override
    public List<Purchase> detailedPurchaseHistoryByUser(String customerId) throws SQLException {
        String sql = "select il.invoiceid, ar.name as artist, al.title, t.name as track_title, " +
            "t.unitprice, g.name as genre from invoiceline as il " +
            "join invoice as i on i.invoiceid = il.invoiceid " +
            "join track as t on t.trackid=il.trackid " +
            "join genre as g on g.genreid = t.genreid " +
            "join album as al on al.albumid = t.albumid " +
            "join artist as ar on ar.artistid = al.artistid " +
            "where customerid = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, customerId);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            List<Purchase> purchases = new ArrayList<>();
            while (rs.next()) {
                purchases.add(fetchdetailedpurchase(rs));
            }
            return purchases;
        }
    }

    private Purchase fetchpurchase(ResultSet resultSet) throws SQLException {
        int invoiceId = resultSet.getInt("invoiceid");
        int numberOfTracks = resultSet.getInt("number_of_tracks");
        int totalPrice = resultSet.getInt("total_price");
        return new Purchase(invoiceId, numberOfTracks, totalPrice);
    }

    private Purchase fetchdetailedpurchase(ResultSet resultSet) throws SQLException {
        int invoiceId = resultSet.getInt("invoiceid");
        String artistName = resultSet.getString("artist");
        String albumTitle = resultSet.getString("title");
        String trackTitle = resultSet.getString("track_title");
        int unitPrice = resultSet.getInt("unitprice");
        String genre = resultSet.getString("genre");
        return new Purchase(invoiceId, artistName, albumTitle, trackTitle, unitPrice, genre);
    }
}
