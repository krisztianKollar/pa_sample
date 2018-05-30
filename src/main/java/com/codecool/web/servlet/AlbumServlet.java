package com.codecool.web.servlet;

import com.codecool.web.dao.AlbumDao;
import com.codecool.web.dao.ArtistDao;
import com.codecool.web.dao.database.DatabaseAlbumDao;
import com.codecool.web.dao.database.DatabaseArtistDao;
import com.codecool.web.dto.MessageDto;
import com.codecool.web.model.Album;
import com.codecool.web.model.Artist;
import com.codecool.web.service.simple.SimpleAlbumService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/protected/albumservlet")
public class AlbumServlet extends AbstractServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection c = getConnection(req.getServletContext())) {
            AlbumDao albumDao = new DatabaseAlbumDao(c);
            ArtistDao artistDao = new DatabaseArtistDao(c);
            SimpleAlbumService service = new SimpleAlbumService(albumDao, artistDao);
            service.addAlbumByArtistName(req.getParameter("albumname"), req.getParameter("artistname"));
            resp.setContentType("application/json");
            sendMessage(resp, HttpServletResponse.SC_OK, new MessageDto("sehr schön!!!"));

        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection c = getConnection(req.getServletContext())) {
            AlbumDao albumDao = new DatabaseAlbumDao(c);
            ArtistDao artistDao = new DatabaseArtistDao(c);
            SimpleAlbumService service = new SimpleAlbumService(albumDao, artistDao);

            List<Album> albums = service.getAlbums();

            resp.setContentType("application/json");
            sendMessage(resp, HttpServletResponse.SC_OK, albums);

        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }
}

