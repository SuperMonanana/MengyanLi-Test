package org.example;

import java.math.BigDecimal;

public class MonitoringService {
    private final int alertThreshold;
    private final AlertingService alertingService;
    private BigDecimal currentRate;
    private int increaseCount;
    private int decreaseCount;

    public MonitoringService(AlertingService alertingService) {
        this.alertingService = alertingService;
        alertThreshold = 2;
        increaseCount = 0;
        decreaseCount = 0;
    }

    public void receiveData(BigDecimal rate) {
        if (currentRate == null) {
            currentRate = rate;
            return;
        }

        if (rate.compareTo(currentRate) < 0) {
            increaseCount = 0;
            decreaseCount++;
        } else if (rate.compareTo(currentRate) > 0) {
            increaseCount++;
            decreaseCount = 0;
        } else {
            increaseCount = 0;
            decreaseCount = 0;
        }
    }

    public void monitor(BigDecimal rate) {
        receiveData(rate);
        if (shouldAlert()) {
            alertingService.alert();
        }
    }

    private boolean shouldAlert() {
        return decreaseCount >= alertThreshold || increaseCount >= alertThreshold;
    }
}
