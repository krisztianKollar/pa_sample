package com.codecool.web.dao.database;

import com.codecool.web.dao.EmployeeDao;
import com.codecool.web.model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseEmployeeDao extends AbstractDao implements EmployeeDao {


    public DatabaseEmployeeDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        String sql = "SELECT e.employeeid, e.firstname, e.lastname, e.title, e.address, e.city, e.country, " +
            "e.postalcode, e.email, count(c.supportrepid) as numofrepresentedcustomers FROM employee as e " +
            "left join customer as c on c.supportrepid = e.employeeid " +
            "group by e.employeeid, e.firstname, e.lastname, e.title, e.address, e.city, e.country, e.postalcode, e.email";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                employees.add(fetchemployee(resultSet));
            }
            return employees;
        }
    }

    private Employee fetchemployee(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("employeeid");
        String firstName = resultSet.getString("firstname");
        String lastName = resultSet.getString("lastname");
        String address = resultSet.getString("address");
        String city = resultSet.getString("city");
        String country = resultSet.getString("country");
        String postalCode = resultSet.getString("postalcode");
        String email = resultSet.getString("email");
        String title = resultSet.getString("title");
        int numOfRepresentedCustomers = resultSet.getInt("numofrepresentedcustomers");
        String fullAddress = country + ", " + postalCode + " " + city + ", " + address;
        return new Employee(id, firstName, lastName, email, fullAddress, title, numOfRepresentedCustomers);
    }
}
