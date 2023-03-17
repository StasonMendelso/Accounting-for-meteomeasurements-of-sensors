package com.stanislav.dto;

import com.stanislav.models.Measurement;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
public class MeasurementResponse {
    private List<MeasurementDTO> measurements;

    public MeasurementResponse() {
    }

    public MeasurementResponse(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
