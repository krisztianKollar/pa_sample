package com.codecool.web.dao;

import com.codecool.web.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

    List<Employee> findAll() throws SQLException;

}
