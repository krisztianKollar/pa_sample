package com.codecool.web.servlet;

import com.codecool.web.dao.TrackDao;
import com.codecool.web.dao.database.DatabaseTrackDao;
import com.codecool.web.model.Track;
import com.codecool.web.service.simple.SimpleTrackService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TrackServlet {

    @WebServlet("/protected/trackservlet")
    public class SchServlets extends AbstractServlet {

        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try (Connection c = getConnection(req.getServletContext())) {
                TrackDao db = new DatabaseTrackDao(c);
                SimpleTrackService service = new SimpleTrackService(db);

                List<Track> tracks = service.getTracks();

                resp.setContentType("application/json");
                sendMessage(resp, HttpServletResponse.SC_OK, tracks);

            } catch (SQLException e) {
                handleSqlError(resp, e);
            }
        }
    }
}
