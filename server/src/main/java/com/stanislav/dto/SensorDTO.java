package com.stanislav.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * @author Stanislav Hlova
 */
public class SensorDTO {

    @NotEmpty(message = "The sensor's name can't be empty.")
    @Size(min = 3, max = 30, message = "The sensor's name size must be between 3 and 30 characters.")
    private String name;

    public SensorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SensorDTO sensorDTO = (SensorDTO) o;

        return Objects.equals(name, sensorDTO.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
