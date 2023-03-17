package com.stanislav.controllers;

import com.stanislav.dto.MeasurementDTORequest;
import com.stanislav.dto.MeasurementResponse;
import com.stanislav.dto.RainyDaysCountResponse;
import com.stanislav.services.ErrorService;
import com.stanislav.services.MeasurementsService;
import com.stanislav.services.TransformerService;
import com.stanislav.utils.exceptions.MeasurementNotAddedException;
import com.stanislav.utils.responses.ErrorResponse;
import com.stanislav.utils.validators.MeasurementDTORequestValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Stanislav Hlova
 */
@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final TransformerService transformerService;
    private final ErrorService errorService;
    private final MeasurementDTORequestValidator measurementDTORequestValidator;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, TransformerService transformerService, ErrorService errorService, MeasurementDTORequestValidator measurementDTORequestValidator) {
        this.measurementsService = measurementsService;
        this.transformerService = transformerService;
        this.errorService = errorService;
        this.measurementDTORequestValidator = measurementDTORequestValidator;
    }

    @GetMapping
    public MeasurementResponse getAllMeasurements() {
        return new MeasurementResponse(transformerService.convertToDTOList(measurementsService.getAll()));
    }

    @GetMapping("/rainyDaysCount")
    public RainyDaysCountResponse getRainyDaysCount() {
        return new RainyDaysCountResponse(measurementsService.getRainyDaysCount());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTORequest measurementDTORequest,
                                                     BindingResult bindingResult) {
        measurementDTORequestValidator.validate(measurementDTORequest, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new MeasurementNotAddedException(errorService.getErrorMessage(bindingResult));
        }
        measurementsService.addMeasurement(transformerService.convertToModel(measurementDTORequest));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(value = MeasurementNotAddedException.class)
    public ResponseEntity<ErrorResponse> handleException(MeasurementNotAddedException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
