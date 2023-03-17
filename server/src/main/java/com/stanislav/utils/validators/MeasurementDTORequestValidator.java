package com.stanislav.utils.validators;

import com.stanislav.dto.MeasurementDTORequest;
import com.stanislav.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Stanislav Hlova
 */
@Component
public class MeasurementDTORequestValidator implements Validator {
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementDTORequestValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTORequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTORequest measurementDTORequest = (MeasurementDTORequest) target;
        if (measurementDTORequest.getSensor() == null) {
            return;
        }
        if (sensorsService.getSensorByName(measurementDTORequest.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "", String.format("The sensor with name %s isn't registered.", measurementDTORequest.getSensor().getName()));
        }
    }
}
