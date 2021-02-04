package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

  public static Connection getConnection() {
    try {
      return DriverManager.getConnection(
          System.getProperty("DbUrl"),
          System.getProperty("DbUser"),
          System.getProperty("DbPass"));
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

}
