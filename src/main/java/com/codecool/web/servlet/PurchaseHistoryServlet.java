package com.codecool.web.servlet;

import com.codecool.web.dao.PurchaseDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabasePurchaseDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.Purchase;
import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/protected/purchasehistoryservlet")
public class PurchaseHistoryServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection c = getConnection(req.getServletContext())) {
            PurchaseDao db = new DatabasePurchaseDao(c);
            int customerId = ((User)req.getSession().getAttribute("user")).getId();

            List<Purchase> purchases = db.purchaseHistoryByUser(customerId);

            resp.setContentType("application/json");
            sendMessage(resp, HttpServletResponse.SC_OK, purchases);

        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection c = getConnection(req.getServletContext())) {
            PurchaseDao db = new DatabasePurchaseDao(c);
            int customerId = ((User)req.getSession().getAttribute("user")).getId();

            List<Purchase> purchases = db.detailedPurchaseHistoryByUser(customerId);

            resp.setContentType("application/json");
            sendMessage(resp, HttpServletResponse.SC_OK, purchases);

        } catch (SQLException e) {
            handleSqlError(resp, e);
        }
    }

}
