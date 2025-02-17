package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MonitoringServiceTest {

    @Test
    void shouldAlert_whenIncreaseTwoTimes() {
        TestAlertingService testAlertingService = new TestAlertingService();
        MonitoringService service = new MonitoringService(testAlertingService);
        service.monitor(new BigDecimal(1));
        assertEquals(0, testAlertingService.alert);

        service.monitor(new BigDecimal(2));
        assertEquals(0, testAlertingService.alert);

        service.monitor(new BigDecimal(3));
        assertEquals(1, testAlertingService.alert);

        service.monitor(new BigDecimal(4));
        assertEquals(2, testAlertingService.alert);
    }
}