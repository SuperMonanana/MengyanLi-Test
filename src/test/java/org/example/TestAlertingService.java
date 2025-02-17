package org.example;

public class TestAlertingService implements AlertingService {
    public int alert;

    @Override
    public void alert() {
        alert++;
    }
}
