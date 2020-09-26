package by.bsuir.sensor.sensor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SensorParamWrapper {
    private String textPart;
    private int page;
    private int perPage;
}
