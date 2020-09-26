package by.bsuir.sensor.sensor;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class SensorService {
    private final ModelMapper modelMapper;
    private final SensorDao sensorDao;

    public SensorDto create(SensorDto sensorDto) {
        Sensor sensor = modelMapper.map(sensorDto, Sensor.class);
        sensorDao.create(sensor);
        return modelMapper.map(sensor, SensorDto.class);
    }

    public SensorDto update(SensorDto sensorDto, long id) {
        sensorDao.find(id).orElseThrow(
                () -> new SensorNotFoundException("There is no sensor with id " + id)
        );
        Sensor sensor = modelMapper.map(sensorDto, Sensor.class);
        sensor.setId(id);
        sensorDao.update(sensor);
        return modelMapper.map(sensor, SensorDto.class);
    }

    public SensorDto delete(long id) {
        Sensor sensor = sensorDao.find(id).orElseThrow(
                () -> new SensorNotFoundException("There is no sensor with id " + id)
        );
        sensorDao.delete(sensor);
        return modelMapper.map(sensor, SensorDto.class);
    }

    public SensorDto findById(long id) {
        Sensor sensor = sensorDao.find(id).orElseThrow(
                () -> new SensorNotFoundException("There is no sensor with id " + id)
        );
        return modelMapper.map(sensor, SensorDto.class);
    }

    public PagedModel<SensorDto> findSensors(SensorParamWrapper params) {
        List<SensorDto> resultList = sensorDao.find(params)
                .stream()
                .map(sensor -> modelMapper.map(sensor,SensorDto.class))
                .collect(Collectors.toList());
        int totalElements = sensorDao.getTotalSensorCount(params);
        int page = params.getPage();
        int perPage = params.getPerPage();
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(perPage, page, totalElements);
        long totalPages = pageMetadata.getTotalPages();
        if(totalElements < page && totalPages != 0) {
            throw new IllegalArgumentException("Invalid page number. There is only " + totalPages + " page/pages");
        }
        return PagedModel.of(resultList, pageMetadata);
    }
}
