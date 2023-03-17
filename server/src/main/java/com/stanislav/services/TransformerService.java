package com.stanislav.services;

import com.stanislav.dto.SensorDTO;
import com.stanislav.models.Sensor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
