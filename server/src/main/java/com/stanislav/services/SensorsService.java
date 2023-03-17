package com.stanislav.services;

import com.stanislav.models.Sensor;
import com.stanislav.repositories.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Service
public class SensorsService {
    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public Optional<Sensor> getSensorByName(String name) {
        return sensorsRepository.findByName(name);
    }

    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }
}
