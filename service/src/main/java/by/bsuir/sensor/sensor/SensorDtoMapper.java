package by.bsuir.sensor.sensor;

import by.bsuir.sensor.sensortype.SensorType;
import by.bsuir.sensor.sensortype.SensorTypeDao;
import by.bsuir.sensor.unit.Unit;
import by.bsuir.sensor.unit.UnitDao;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class SensorDtoMapper {
    private final ModelMapper mapper;
    private final UnitDao unitDao;
    private final SensorTypeDao sensorTypeDao;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Sensor.class, SensorDto.class)
                .addMappings(m -> {
                    m.skip(SensorDto::setSensorType);
                    m.skip(SensorDto::setUnit);
                }).setPostConverter(toDtoConverter());
        mapper.createTypeMap(SensorDto.class, Sensor.class)
                .addMappings(m -> {
                    m.skip(Sensor::setSensorType);
                    m.skip(Sensor::setUnit);
                })
                .setPostConverter(toEntityConverter());
    }

    public Converter<Sensor, SensorDto> toDtoConverter() {
        return context -> {
            Sensor source = context.getSource();
            SensorDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<SensorDto, Sensor> toEntityConverter() {
        return context -> {
            SensorDto source = context.getSource();
            Sensor destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(SensorDto source, Sensor destination) {
        String type = source.getSensorType();
        SensorType sensorType = sensorTypeDao.find(type).orElseThrow(
                () -> new IllegalArgumentException("There is no sensor type with name " + type)
        );
        destination.setSensorType(sensorType);
        String unitName = source.getUnit();
        Unit unit = unitDao.find(unitName).orElseThrow(
                () -> new IllegalArgumentException("There is no unit with name " + unitName)
        );
        destination.setUnit(unit);
    }

    public void mapSpecificFields(Sensor source, SensorDto destination) {
        String type = source.getSensorType().getType();
        destination.setSensorType(type);
        String unit = source.getUnit().getUnitName();
        destination.setUnit(unit);
    }
}
