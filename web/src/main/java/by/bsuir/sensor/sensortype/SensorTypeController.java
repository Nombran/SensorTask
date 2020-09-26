package by.bsuir.sensor.sensortype;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sensor-types")
@AllArgsConstructor
public class SensorTypeController {
    private final SensorTypeService sensorTypeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<String> findAll() {
        return sensorTypeService.findAll();
    }
}
