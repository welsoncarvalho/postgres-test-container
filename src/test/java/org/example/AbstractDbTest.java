package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.example.db.DbConnection;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractDbTest {

  private PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest")
      .withDatabaseName("test")
      .withUsername("test")
      .withPassword("test");

  public void startDb() {
    this.container.start();

    System.setProperty("DbUrl", this.container.getJdbcUrl());
    System.setProperty("DbUser", this.container.getUsername());
    System.setProperty("DbPass", this.container.getPassword());
    System.setProperty("DbSchema", "wts_test");

    initDatabase();
  }

  private void initDatabase() {
    try (Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement()) {

      statement.execute("create schema wts_test");
      statement.execute("create table wts_test.timer (vin varchar(50),"
          + "end_time timestamp,"
          + "event_vehicle_time timestamp,"
          + "event_received_time timestamp,"
          + "constraint pk_timer primary key (vin))");

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void stopDb() {
    this.container.stop();
  }

}
