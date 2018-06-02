package com.codecool.web.dao;

import com.codecool.web.model.Purchase;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseDao {

    List<Purchase> purchaseHistoryByUser(String customerId) throws SQLException;

    List<Purchase> detailedPurchaseHistoryByUser(String customerId) throws SQLException;
}
