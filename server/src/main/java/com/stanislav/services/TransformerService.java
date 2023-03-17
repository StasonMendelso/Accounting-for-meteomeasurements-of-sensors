package com.stanislav.services;

import com.stanislav.dto.MeasurementDTO;
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

    public MeasurementDTO convertToDTO(Measurement measurement) {
        MeasurementDTO measurementDTO = modelMapper.map(measurement, MeasurementDTO.class);
        measurementDTO.setSensor(convertToDTO(measurement.getSensor()));
        return measurementDTO;
    }

    public List<MeasurementDTO> convertToDTOList(List<Measurement> measurements) {
        return measurements.stream().map(this::convertToDTO).toList();
    }
}
