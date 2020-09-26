package by.bsuir.sensor.unit;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/units")
@AllArgsConstructor
public class UnitController {
    private final UnitService unitService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<String> findAll() {
        return unitService.findAll();
    }
}
