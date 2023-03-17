package com.stanislav.controllers;

import com.stanislav.dto.SensorDTO;
import com.stanislav.services.ErrorService;
import com.stanislav.services.SensorsService;
import com.stanislav.services.TransformerService;
import com.stanislav.utils.exceptions.SensorNotRegisteredException;
import com.stanislav.utils.responses.ErrorResponse;
import com.stanislav.utils.validators.SensorDTOValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Stanislav Hlova
 */
@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private final SensorsService sensorsService;
    private final TransformerService transformerService;
    private final ErrorService errorService;
    private final SensorDTOValidator sensorDTOValidator;

    @Autowired
    public SensorsController(SensorsService sensorsService, TransformerService transformerService, ErrorService errorService, SensorDTOValidator sensorDTOValidator) {
        this.sensorsService = sensorsService;
        this.transformerService = transformerService;
        this.errorService = errorService;
        this.sensorDTOValidator = sensorDTOValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO,
                                               BindingResult bindingResult) {
        sensorDTOValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new SensorNotRegisteredException(errorService.getErrorMessage(bindingResult));
        }
        sensorsService.save(transformerService.convertToModel(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SensorNotRegisteredException.class)
    public ResponseEntity<ErrorResponse> handleException(SensorNotRegisteredException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
