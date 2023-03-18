package org.stanislav.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * @author Stanislav Hlova
 */
public class MeasurementDTO {
    private double temperature;
    private boolean raining;
    @JsonAlias(value = "sensor")
    @JsonProperty(value = "sensor")
    private SensorDTO sensorDTO;
    @JsonAlias(value = "time")
    private LocalDateTime time;

    public MeasurementDTO() {
    }

    public MeasurementDTO(double temperature, boolean raining, SensorDTO sensorDTO) {
        this.temperature = temperature;
        this.raining = raining;
        this.sensorDTO = sensorDTO;
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

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "temperature=" + temperature +
                ", raining=" + raining +
                ", sensorDTO=" + sensorDTO +
                ", timestamp=" + time +
                '}';
    }
}
