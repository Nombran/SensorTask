package by.bsuir.sensor.sensortype;

import by.bsuir.sensor.unit.Unit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SensorTypeDao {
    @PersistenceContext
    private final EntityManager em;
    private static final String FIND_UNIT_BY_NAME = "select t from SensorType t where t.type=:type";
    private static final String FIND_ALL = "select t from SensorType t";

    public Optional<SensorType> find(String type) {
        TypedQuery<SensorType> query = em.createQuery(FIND_UNIT_BY_NAME, SensorType.class);
        query.setParameter("type", type);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public List<SensorType> findAll() {
        TypedQuery<SensorType> query = em.createQuery(FIND_ALL, SensorType.class);
        return query.getResultList();
    }
}
