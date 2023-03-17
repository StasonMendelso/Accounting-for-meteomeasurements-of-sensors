package com.stanislav.dto;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
public class MeasurementResponse {
    private List<MeasurementDTOResponse> measurements;

    public MeasurementResponse() {
    }

    public MeasurementResponse(List<MeasurementDTOResponse> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementDTOResponse> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTOResponse> measurements) {
        this.measurements = measurements;
    }
}
