package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseUserDao extends AbstractDao implements UserDao {

    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT c.customerid, c.firstname, c.lastname, c.company, c.address, c.city, c.country, c.postalcode, " +
            "c.company, e.email as salesrepresentativecontact, count(i.customerid) as numberofpurchases, c.email FROM customer as c " +
            "inner join employee as e on c.supportrepid = e.employeeid " +
            "inner join invoice as i on i.customerid = c.customerid " +
            "group by c.customerid, c.firstname, c.lastname, c.company, c.address, c.city, c.country, " +
            "c.postalcode, c.company, e.email, c.email;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(fetchuser(resultSet));
            }
            return users;
        }
    }

//    @Override
//    public User findByEmail(String email) throws SQLException {
//        if (email == null || "".equals(email)) {
//            throw new IllegalArgumentException("Email cannot be null or empty");
//        }
//        String sql = "SELECT customerid, email from customer where email = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, email);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//
//
//                if (resultSet.next()) {
//                    return fetchuser(resultSet);
//                }
//            }
//        }
//        return null;
//    }



    @Override
    public User findByEmail(String email) throws SQLException {
        if (email == null || "".equals(email)) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String sql = "SELECT c.customerid, c.firstname, c.lastname, c.company, c.address, c.city, c.country, c.postalcode, " +
            "c.company, e.email as salesrepresentativecontact, count(i.customerid) as numberofpurchases, c.email FROM customer as c " +
            "inner join employee as e on c.supportrepid = e.employeeid " +
            "inner join invoice as i on i.customerid = c.customerid " +
            "where c.email = ?" +
            "group by c.customerid, c.firstname, c.lastname, c.company, c.address, c.city, c.country, " +
            "c.postalcode, c.company, e.email, c.email";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {


                if (resultSet.next()) {
                    return fetchuser(resultSet);
                }
            }
        }
        return null;
    }

//    private User fetchuser(ResultSet resultSet) throws SQLException {
//        int id = resultSet.getInt("customerid");
//        String email = resultSet.getString("email");
//        return new User(id, email);
//    }


    private User fetchuser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("customerid");
        String firstName = resultSet.getString("firstname");
        String lastName = resultSet.getString("lastname");
        String address = resultSet.getString("address");
        String city = resultSet.getString("city");
        String country = resultSet.getString("country");
        String postalCode = resultSet.getString("postalcode");
        String email = resultSet.getString("email");
        String company = resultSet.getString("company");
        String salesReprContactInf = resultSet.getString("salesrepresentativecontact");
        int numOfPurchases = resultSet.getInt("numberofpurchases");
        String fullAddress = country + ", " + postalCode + " " + city + ", " + address;
        return new User(id, firstName, lastName, email, fullAddress, company, salesReprContactInf, numOfPurchases);
    }
}
