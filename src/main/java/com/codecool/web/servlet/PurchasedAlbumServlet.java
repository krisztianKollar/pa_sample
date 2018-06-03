package com.codecool.web.servlet;

import com.codecool.web.dao.AlbumDao;
import com.codecool.web.dao.ArtistDao;
import com.codecool.web.dao.database.DatabaseAlbumDao;
import com.codecool.web.dao.database.DatabaseArtistDao;
import com.codecool.web.dto.MessageDto;
import com.codecool.web.model.Album;
import com.codecool.web.model.User;
import com.codecool.web.service.simple.SimpleAlbumService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/protected/purchalbumservlet")
public class PurchasedAlbumServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection c = getConnection(req.getServletContext())) {
            AlbumDao albumDao = new DatabaseAlbumDao(c);
            ArtistDao artistDao = new DatabaseArtistDao(c);
            SimpleAlbumService service = new SimpleAlbumService(albumDao, artistDao);
            int customerId = ((User)req.getSession().getAttribute("user")).getId();
            List<Album> albums = service.getPurchasedAlbums(customerId);
            resp.setContentType("application/json");
            sendMessage(resp, HttpServletResponse.SC_OK, albums);
            System.out.println(albums);

        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }
}

