package com.stanislav.services;

import com.stanislav.models.Measurement;
import com.stanislav.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public List<Measurement> getAll() {
        return measurementsRepository.findAll();
    }

    public Long getRainyDaysCount() {
        return measurementsRepository.findAll().stream()
                .filter(Measurement::isRaining)
                .map(measurement -> measurement.getTime().toInstant(ZoneOffset.UTC).truncatedTo(ChronoUnit.DAYS))
                .distinct()
                .count();
    }

    @Transactional
    public void addMeasurement(Measurement measurement) {
        measurement.setTime(LocalDateTime.now());
        measurement.setSensor(sensorsService.getSensorByName(measurement.getSensor().getName()).get());
        measurementsRepository.save(measurement);
    }
}
