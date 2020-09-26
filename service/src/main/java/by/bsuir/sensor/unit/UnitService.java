package by.bsuir.sensor.unit;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UnitService {
    private final UnitDao unitDao;

    public List<String> findAll() {
        return unitDao
                .findAll()
                .stream()
                .map(Unit::getUnitName)
                .collect(Collectors.toList());
    }
}
