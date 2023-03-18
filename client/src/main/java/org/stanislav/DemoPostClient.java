package org.stanislav;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.stanislav.dto.MeasurementDTO;
import org.stanislav.dto.SensorDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

/**
 * Hello world!
 */
public class DemoPostClient {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        final String sensorName = "Test3 Sensor";
        try {
            ResponseEntity<String> responseEntity = registerSensor(sensorName);
            System.out.println("Успішна регістрація.\n" + responseEntity);
        } catch (HttpClientErrorException | ResourceAccessException exception) {
            System.out.println("Помилка регістрації сенсора.");
            System.out.println(exception.getMessage());
            return;
        }

        try {
            Random random = new Random();
            double deltaTemperature = 20;
            for (int i = 1; i <= 1000; i++) {
                Thread.sleep(1000);
                MeasurementDTO measurementDTO = new MeasurementDTO(random.nextDouble() * (deltaTemperature + 1), random.nextBoolean(), new SensorDTO(sensorName));
                ResponseEntity<String> responseEntity = sendMeasurement(measurementDTO);
                System.out.println("Замір надіслано:\n" + responseEntity);
            }
        } catch (HttpClientErrorException| ResourceAccessException exception) {
            System.out.println("Помилка відправки заміру:");
            System.out.println(exception.getMessage());
        }

    }

    private static ResponseEntity<String> sendMeasurement(MeasurementDTO measurementDTO) throws URISyntaxException {
        URI url = new URI("http://localhost:8080/weather-station/measurements/add");
        return new RestTemplate().postForEntity(url, measurementDTO, String.class);
    }

    private static ResponseEntity<String> registerSensor(String name) throws URISyntaxException {
        URI url = new URI("http://localhost:8080/weather-station/sensors/registration");
        return new RestTemplate().postForEntity(url, new SensorDTO(name), String.class);
    }
}
