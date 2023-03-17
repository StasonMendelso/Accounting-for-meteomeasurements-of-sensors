package com.stanislav.controllers;

import com.stanislav.dto.MeasurementResponse;
import com.stanislav.services.MeasurementsService;
import com.stanislav.services.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final TransformerService transformerService;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, TransformerService transformerService) {
        this.measurementsService = measurementsService;
        this.transformerService = transformerService;
    }

    @GetMapping
    public MeasurementResponse getAllMeasurements() {
        return new MeasurementResponse(transformerService.convertToDTOList(measurementsService.getAll()));
    }

}
