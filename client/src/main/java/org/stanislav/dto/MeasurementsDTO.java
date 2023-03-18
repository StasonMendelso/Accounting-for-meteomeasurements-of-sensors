package org.stanislav.dto;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
public class MeasurementsDTO {
    private List<MeasurementDTO> measurements;

    public MeasurementsDTO() {
    }

    public MeasurementsDTO(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
