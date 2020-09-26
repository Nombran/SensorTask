package by.bsuir.sensor.sensor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sensors")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "model")
    private String model;
    @Column(name = "range_from")
    private int rangeFrom;
    @Column(name = "range_to")
    private int rangeTo;
    @Column(name = "location")
    private String location;
    @Column(name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_type", nullable = false)
    private SensorType sensorType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit", nullable = false)
    private Unit unit;
}
