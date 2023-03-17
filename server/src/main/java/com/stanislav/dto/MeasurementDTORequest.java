package com.stanislav.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author Stanislav Hlova
 */
public class MeasurementDTORequest {
    @Min(value = -100, message = "The temperature can't be less than -100")
    @Max(value = 100, message = "The temperature can't be more than 100")
    @NotNull(message = "The temperature value can't be empty")
    private Double temperature;

    @NotNull(message = "The raining value can't be empty")
    private Boolean raining;
    @NotNull(message = "The sensor value can't be empty")
    @Valid
    private SensorDTO sensor;

    public MeasurementDTORequest() {
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
}
