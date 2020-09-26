package by.bsuir.sensor.sensor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SensorDao {
    @PersistenceContext
    private final EntityManager em;

    public void create(Sensor sensor) {
        em.persist(sensor);
    }

    public void update(Sensor sensor) {
        em.merge(sensor);
    }

    public void delete(Sensor sensor) {
        em.remove(sensor);
    }

    public Optional<Sensor> find(long id) {
        return Optional.ofNullable(em.find(Sensor.class,id));
    }

    public List<Sensor> find(SensorParamWrapper params) {
        CriteriaQuery<Sensor> query = prepareSensorQuery(params);

        int page = params.getPage();
        int perPage = params.getPerPage();

        return em.createQuery(query)
                .setFirstResult((page-1) * perPage)
                .setMaxResults(perPage)
                .getResultList();
    }

    private CriteriaQuery<Sensor> prepareSensorQuery(SensorParamWrapper params) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Sensor> query = cb.createQuery(Sensor.class);
        Root<Sensor> root = query.from(Sensor.class);
        query.select(root);

        List<Predicate> predicates = new ArrayList<>();

        String textPart = params.getTextPart();
        if(textPart != null && !textPart.isEmpty()) {
            textPart = textPart.toLowerCase();
            Predicate predicateForName = cb.like(cb.lower(root.get(Sensor_.NAME)), "%" + textPart + "%");
            Predicate predicateForModel = cb.like(cb.lower(root.get(Sensor_.MODEL)), "%" + textPart + "%");
            Predicate predicateForDescription = cb.like(cb.lower(root.get(Sensor_.DESCRIPTION)), "%" + textPart + "%");
            Predicate predicateForLocation = cb.like(cb.lower(root.get(Sensor_.LOCATION)), "%" + textPart + "%");
            Predicate predicateForRangeFrom = cb.like(root.get(Sensor_.RANGE_FROM), "%" + textPart + "%");
            Predicate predicateForRangeTo = cb.like(root.get(Sensor_.RANGE_TO), "%" + textPart + "%");
            Predicate predicateForType = cb.like(root.get(SensorType_.TYPE),"%" + textPart + "%");
            Predicate predicateForUnit = cb.like(root.get(Unit_.UNIT_NAME),"%" + textPart + "%");
            Predicate textPartPredicate = cb.or(predicateForName, predicateForModel, predicateForDescription, predicateForLocation,
                    predicateForRangeFrom, predicateForRangeTo, predicateForType, predicateForUnit);
            predicates.add(textPartPredicate);
        }

        if(predicates.size() > 0) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }
        return query;
    }

    public int getTotalSensorCount(SensorParamWrapper params) {
        CriteriaQuery<Sensor> query = prepareSensorQuery(params);

        int page = params.getPage();
        int perPage = params.getPerPage();

        return em.createQuery(query)
                .setFirstResult((page-1) * perPage)
                .setMaxResults(perPage)
                .getResultList().size();
    }
}
