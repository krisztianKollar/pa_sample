package com.codecool.web.servlet;

import com.codecool.web.dao.ArtistDao;
import com.codecool.web.dao.EmployeeDao;
import com.codecool.web.dao.database.DatabaseArtistDao;
import com.codecool.web.dao.database.DatabaseEmployeeDao;
import com.codecool.web.model.Artist;
import com.codecool.web.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/protected/artistsservlet")
public class ArtistsServlet extends AbstractServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection c = getConnection(req.getServletContext())) {
            ArtistDao db = new DatabaseArtistDao(c);

            List<Artist> artists = db.findAllArtist();

            resp.setContentType("application/json");
            sendMessage(resp, HttpServletResponse.SC_OK, artists);

        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }
}
