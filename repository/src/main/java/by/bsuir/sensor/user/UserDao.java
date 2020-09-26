package by.bsuir.sensor.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class UserDao {
    @PersistenceContext
    private final EntityManager em;
    private static final String FIND_USER_BY_LOGIN = "select u from User u where u.login=:login";

    @Autowired
    public UserDao(EntityManager em) {
        this.em = em;
    }

    public void create(User user) {
        em.persist(user);
    }

    public Optional<User> find(long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    public Optional<User> findByLogin(String login) {
        TypedQuery<User> typedQuery = em.createQuery(FIND_USER_BY_LOGIN, User.class);
        typedQuery.setParameter("login", login);
        try {
            return Optional.ofNullable(typedQuery.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
