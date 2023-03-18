package com.stanislav.dto;

import java.time.LocalDateTime;

/**
 * @author Stanislav Hlova
 */
public class MeasurementDTOResponse {
    private double temperature;

    private boolean raining;

    private SensorDTO sensor;

    private LocalDateTime time;

    public MeasurementDTOResponse() {
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
