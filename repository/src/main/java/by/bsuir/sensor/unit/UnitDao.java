package by.bsuir.sensor.unit;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UnitDao {
    @PersistenceContext
    private final EntityManager em;
    private static final String FIND_UNIT_BY_NAME = "select u from Unit u where u.unitName=:unit";
    private static final String FIND_ALL = "select u from Unit u";

    public Optional<Unit> find(String unitName) {
        TypedQuery<Unit> query = em.createQuery(FIND_UNIT_BY_NAME, Unit.class);
        query.setParameter("unit", unitName);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public List<Unit> findAll() {
        TypedQuery<Unit> query = em.createQuery(FIND_ALL, Unit.class);
        return query.getResultList();
    }
}
