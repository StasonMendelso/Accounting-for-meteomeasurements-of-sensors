package org.stanislav;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.springframework.web.client.RestTemplate;
import org.stanislav.dto.MeasurementDTO;
import org.stanislav.dto.MeasurementsDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Stanislav Hlova
 */
public class DemoGetClient {
    public static void main(String[] args) throws URISyntaxException {
        URI url = new URI("http://localhost:8080/weather-station/measurements");

        List<MeasurementDTO> measurements = getAllMeasurements(url);
        measurements.forEach(System.out::println);
        drawChart(measurements);
    }


    private static List<MeasurementDTO> getAllMeasurements(URI url) {
        RestTemplate restTemplate = new RestTemplate();
        MeasurementsDTO measurementsDTO = restTemplate.getForObject(url, MeasurementsDTO.class);
        if (measurementsDTO == null || measurementsDTO.getMeasurements() == null) {
            return Collections.emptyList();
        }
        return measurementsDTO.getMeasurements();
    }

    private static void drawChart(List<MeasurementDTO> measurements) {
        XYChart chart = buildChart(getMapWithSensorsAndMeasurements(measurements));
        new SwingWrapper<>(chart).displayChart();
    }

    private static XYChart buildChart(Map<String, List<MeasurementDTO>> sensorsMap) {
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Temperature chart").xAxisTitle("Date").yAxisTitle("Temperature").build();
        chart.getStyler().setDatePattern("dd-MM-YYYY hh-mm");
        chart.getStyler().setDecimalPattern("#0.000");

        for (Map.Entry<String, List<MeasurementDTO>> entry : sensorsMap.entrySet()) {
            List<MeasurementDTO> measurementsOfSensor = entry.getValue();
            measurementsOfSensor.sort((Comparator.comparing(MeasurementDTO::getTime)));
            List<Date> dates = new ArrayList<>();
            List<Double> temperatures = new ArrayList<>();
            for (MeasurementDTO measurement : measurementsOfSensor) {
                dates.add(Date.from(measurement.getTime().toInstant(ZoneOffset.UTC)));
                temperatures.add(measurement.getTemperature());
            }
            chart.addSeries(entry.getKey(), dates, temperatures);
        }
        return chart;
    }

    private static Map<String, List<MeasurementDTO>> getMapWithSensorsAndMeasurements(List<MeasurementDTO> measurements) {
        Map<String, List<MeasurementDTO>> sensorsMap = new HashMap<>();
        for (MeasurementDTO measurement : measurements) {
            List<MeasurementDTO> measurementsOfSensor = sensorsMap.get(measurement.getSensorDTO().getName());
            if (measurementsOfSensor != null) {
                measurementsOfSensor.add(measurement);
            } else {
                sensorsMap.put(measurement.getSensorDTO().getName(), new ArrayList<>(Collections.singleton(measurement)));
            }
        }
        return sensorsMap;
    }
}
