package com.codecool.web.servlet;

import com.codecool.web.dao.TrackDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseTrackDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.Track;
import com.codecool.web.model.User;
import com.codecool.web.service.simple.SimpleTrackService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/protected/customersservlet")
public class CustomersServlet extends AbstractServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection c = getConnection(req.getServletContext())) {
            UserDao db = new DatabaseUserDao(c);

            List<User> users = db.findAll();

            resp.setContentType("application/json");
            sendMessage(resp, HttpServletResponse.SC_OK, users);

        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }
}
