package by.bsuir.sensor.sensor;

import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class SensorDtoMapper {
    private final ModelMapper mapper;

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
        destination.setSensorType(new SensorType(type));
        String unit = source.getUnit();
        destination.setUnit(new Unit(unit));
    }

    public void mapSpecificFields(Sensor source, SensorDto destination) {
        String type = source.getSensorType().getType();
        destination.setSensorType(type);
        String unit = source.getUnit().getUnitName();
        destination.setUnit(unit);
    }
}
