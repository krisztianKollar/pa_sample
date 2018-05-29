package com.codecool.web.servlet;

import com.codecool.web.dao.EmployeeDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseEmployeeDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.Employee;
import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/protected/employeesservlet")
public class EmployeesServlet extends AbstractServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection c = getConnection(req.getServletContext())) {
            EmployeeDao db = new DatabaseEmployeeDao(c);

            List<Employee> employees = db.findAll();

            resp.setContentType("application/json");
            sendMessage(resp, HttpServletResponse.SC_OK, employees);

        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }
}
