package by.bsuir.sensor.sensor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class SensorDto {
    private long id;
    @NonNull
    @NotBlank
    @Size(min = 1, max = 30)
    private String name;
    @NonNull
    @NotBlank
    @Size(min = 1, max = 15)
    private String model;
    @NonNull
    private Integer rangeFrom;
    @NonNull
    private Integer rangeTo;
    @NonNull
    @NotBlank
    @Size(min = 1, max = 40)
    private String location;
    @NonNull
    @NotBlank
    @Size(min = 1, max = 200)
    private String description;
    @NonNull
    @NotBlank
    private String sensorType;
    @NonNull
    @NotBlank
    private String unit;
}
