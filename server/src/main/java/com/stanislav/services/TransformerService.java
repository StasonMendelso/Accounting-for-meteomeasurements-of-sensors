package com.stanislav.services;

import com.stanislav.dto.MeasurementDTORequest;
import com.stanislav.dto.MeasurementDTOResponse;
import com.stanislav.dto.SensorDTO;
import com.stanislav.models.Measurement;
import com.stanislav.models.Sensor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Service
public class TransformerService {
    private final ModelMapper modelMapper;

    @Autowired
    public TransformerService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Sensor convertToModel(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    public SensorDTO convertToDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    public MeasurementDTOResponse convertToDTO(Measurement measurement) {
        MeasurementDTOResponse measurementDTOResponse = modelMapper.map(measurement, MeasurementDTOResponse.class);
        measurementDTOResponse.setSensor(convertToDTO(measurement.getSensor()));
        return measurementDTOResponse;
    }

    public List<MeasurementDTOResponse> convertToDTOList(List<Measurement> measurements) {
        return measurements.stream().map(this::convertToDTO).toList();
    }

    public Measurement convertToModel(MeasurementDTORequest measurementDTORequest) {
        Measurement measurement = modelMapper.map(measurementDTORequest, Measurement.class);
        measurement.setSensor(convertToModel(measurementDTORequest.getSensor()));
        return measurement;
    }
}
