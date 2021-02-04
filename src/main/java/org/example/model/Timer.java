package org.example.model;

import java.time.Instant;

public class Timer {

  private String vin;
  private Instant endTime;
  private Instant eventVehicleTime;
  private Instant eventReceivedTime;

  public Timer() {}

  public Timer(String vin, Instant endTime, Instant eventVehicleTime, Instant eventReceivedTime) {
    this.vin = vin;
    this.endTime = endTime;
    this.eventVehicleTime = eventVehicleTime;
    this.eventReceivedTime = eventReceivedTime;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public Instant getEndTime() {
    return endTime;
  }

  public void setEndTime(Instant endTime) {
    this.endTime = endTime;
  }

  public Instant getEventVehicleTime() {
    return eventVehicleTime;
  }

  public void setEventVehicleTime(Instant eventVehicleTime) {
    this.eventVehicleTime = eventVehicleTime;
  }

  public Instant getEventReceivedTime() {
    return eventReceivedTime;
  }

  public void setEventReceivedTime(Instant eventReceivedTime) {
    this.eventReceivedTime = eventReceivedTime;
  }
}
