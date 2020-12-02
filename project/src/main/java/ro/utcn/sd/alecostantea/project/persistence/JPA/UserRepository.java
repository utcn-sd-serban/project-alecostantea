package ro.utcn.sd.alecostantea.project.persistence.JPA;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alecostantea.project.model.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager entityManager;

    public User save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
            return user;
        } else {
            return entityManager.merge(user);
        }
    }

    public Optional<User> findByID(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    public void remove(User user) {
        entityManager.remove(user);
    }

    public List<User> findALL() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        criteriaQuery.select(criteriaQuery.from(User.class));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Optional<User> findByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        List<User> users = entityManager.createQuery(criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("username"), username))).getResultList();
        return Optional.ofNullable(users.size() == 0 ? null : users.get(0));

    }


}
