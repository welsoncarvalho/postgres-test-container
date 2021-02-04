package org.example;

import java.time.Instant;
import org.example.model.Timer;
import org.example.persistence.TimerPersistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestInsertTimer extends AbstractDbTest {

  @BeforeEach
  public void before() {
    super.startDb();
  }

  @AfterEach
  public void after() {
    super.stopDb();
  }

  @Test
  public void shouldBeInsertTimer() {
    Timer timer = new Timer("VIN#1234", Instant.now(), Instant.now(), Instant.now());
    TimerPersistence persistence = new TimerPersistence();

    persistence.insert(timer);
    Timer timerDb = persistence.findById(timer.getVin());

    Assertions.assertEquals(timer.getVin(), timerDb.getVin());
    Assertions.assertEquals(timer.getEndTime(), timerDb.getEndTime());
    Assertions.assertEquals(timer.getEventVehicleTime(), timerDb.getEventVehicleTime());
    Assertions.assertEquals(timer.getEventReceivedTime(), timerDb.getEventReceivedTime());
  }

}
