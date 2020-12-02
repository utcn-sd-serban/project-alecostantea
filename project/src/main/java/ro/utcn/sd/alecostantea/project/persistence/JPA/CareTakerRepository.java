package ro.utcn.sd.alecostantea.project.persistence.JPA;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alecostantea.project.model.CareTaker;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CareTakerRepository {
    private final EntityManager entityManager;

    public CareTaker save(CareTaker careTaker) {
        if (careTaker.getId() == null) {
            entityManager.persist(careTaker);
            return careTaker;
        } else {
            return entityManager.merge(careTaker);
        }
    }

    public Optional<CareTaker> findByID(int id) {
        return Optional.ofNullable(entityManager.find(CareTaker.class, id));
    }

    public void remove(CareTaker careTaker) {
        entityManager.remove(careTaker);
    }

    public List<CareTaker> findALL() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CareTaker> criteriaQuery = criteriaBuilder.createQuery(CareTaker.class);
        criteriaQuery.select(criteriaQuery.from(CareTaker.class));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Optional<CareTaker> findByUsername(String careTakername) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(CareTaker.class);
        Root<CareTaker> careTakerRoot = criteriaQuery.from(CareTaker.class);
        List<CareTaker> careTakers = entityManager.createQuery(criteriaQuery.select(careTakerRoot).where(criteriaBuilder.equal(careTakerRoot.get("username"), careTakername))).getResultList();
        return Optional.ofNullable(careTakers.size() == 0 ? null : careTakers.get(0));

    }
}
