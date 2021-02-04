package org.example.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.example.db.DbConnection;
import org.example.model.Timer;

public class TimerPersistence {

  private final Connection connection;
  private final String schema;

  public TimerPersistence() {
    this.connection = DbConnection.getConnection();
    this.schema = System.getProperty("DbSchema");
  }

  public void insert(Timer timer) {
    try (PreparedStatement pst = this.connection.prepareStatement(insertSql())) {

      pst.setString(1, timer.getVin());
      pst.setTimestamp(2, Timestamp.from(timer.getEndTime()));
      pst.setTimestamp(3, Timestamp.from(timer.getEventVehicleTime()));
      pst.setTimestamp(4, Timestamp.from(timer.getEventReceivedTime()));

      pst.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Timer findById(String vin) {
    try (PreparedStatement pst = this.connection.prepareStatement(findByIdSql())) {

      pst.setString(1, vin);

      ResultSet rs = pst.executeQuery();

      if (rs.next()) {
        Timer timer = new Timer();

        timer.setVin(rs.getString(1));
        timer.setEndTime(rs.getTimestamp(2).toInstant());
        timer.setEventVehicleTime(rs.getTimestamp(3).toInstant());
        timer.setEventReceivedTime(rs.getTimestamp(4).toInstant());

        return timer;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  private String insertSql() {
    return "insert into "
        + this.schema
        + ".timer(vin, end_time, event_vehicle_time, event_received_time) values (?, ?, ?, ?) "
        + "on conflict (vin) do update set "
        + "end_time = EXCLUDED.end_time, "
        + "event_vehicle_time = EXCLUDED.event_vehicle_time, "
        + "event_received_time = EXCLUDED.event_received_time";
  }

  private String findByIdSql() {
    return "select vin, end_time, event_vehicle_time, event_received_time from "
        + this.schema
        + ".timer where vin = ?";
  }

}
