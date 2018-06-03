package com.codecool.web.dao;

import com.codecool.web.model.Purchase;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseDao {

    List<Purchase> purchaseHistoryByUser(int customerId) throws SQLException;

    List<Purchase> detailedPurchaseHistoryByUser(int customerId) throws SQLException;
}
