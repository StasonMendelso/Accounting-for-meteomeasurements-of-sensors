package com.stanislav.utils.validators;

import com.stanislav.dto.SensorDTO;
import com.stanislav.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Stanislav Hlova
 */
@Component
public class SensorDTOValidator implements Validator {
    private final SensorsService sensorsService;

    @Autowired
    public SensorDTOValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if (sensorsService.getSensorByName(sensorDTO.getName()).isPresent()) {
            errors.rejectValue("name", "", String.format("The sensor with name %s is already existed.", sensorDTO.getName()));
        }
    }
}
