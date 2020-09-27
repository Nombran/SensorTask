package by.bsuir.sensor.sensor;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping(value = "/api/v1/sensors")
@AllArgsConstructor
public class SensorController {
    private final SensorService sensorService;
    private final SensorHateoasUtil sensorHateoasUtil;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<SensorDto> findSensors(@RequestParam(name = "page", required = false, defaultValue = "1")
                                                 @Min(value = 1, message = "page number must be greater or equal to 1")
                                                         Integer page,
                                             @RequestParam(name = "perPage", required = false, defaultValue = "50")
                                                 @Min(value = 1, message = "perPage param must be greater or equal to 1")
                                                         Integer perPage,
                                             @RequestParam(name = "textPart", required = false)
                                                         String textPart
    ) {
        SensorParamWrapper params = new SensorParamWrapper(textPart, page, perPage);
        PagedModel<SensorDto> pagedModel = sensorService.findSensors(params);
        sensorHateoasUtil.createPaginationLinks(pagedModel, textPart);
        return pagedModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_ADMIN")
    public SensorDto create(@Valid @RequestBody SensorDto sensorDto) {
        sensorDto = sensorService.create(sensorDto);
        return sensorHateoasUtil.createSelfRelLink(sensorDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public SensorDto update(@Valid @RequestBody SensorDto sensorDto, @PathVariable(name = "id")long id) {
        sensorDto = sensorService.update(sensorDto, id);
        return sensorHateoasUtil.createSelfRelLink(sensorDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public SensorDto delete(@PathVariable(name = "id")long id) {
        return sensorService.delete(id);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SensorDto findById(@PathVariable(name = "id")long id) {
        return sensorService.findById(id);
    }
}
