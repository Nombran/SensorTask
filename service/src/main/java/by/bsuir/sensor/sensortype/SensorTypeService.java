package by.bsuir.sensor.sensortype;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SensorTypeService {
    private final SensorTypeDao dao;

    public List<String> findAll() {
        return dao
                .findAll()
                .stream()
                .map(SensorType::getType)
                .collect(Collectors.toList());
    }
}
