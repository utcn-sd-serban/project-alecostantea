package ro.utcn.sd.alecostantea.project.persistence.JPA;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alecostantea.project.model.PetType;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PetTypeRepository {
    private final EntityManager entityManager;

    public PetType save(PetType petType) {
        if (petType.getId() == null) {
            entityManager.persist(petType);
            return petType;
        } else {
            return entityManager.merge(petType);
        }
    }

    public Optional<PetType> findByID(int id) {
        return Optional.ofNullable(entityManager.find(PetType.class, id));
    }

    public void remove(PetType petType) {
        entityManager.remove(petType);
    }

    public List<PetType> findALL() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PetType> criteriaQuery = criteriaBuilder.createQuery(PetType.class);
        criteriaQuery.select(criteriaQuery.from(PetType.class));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Optional<PetType> findByName(String name){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(PetType.class);
        Root<PetType> petTypeRoot = criteriaQuery.from(PetType.class);
        List<PetType> petTypes = entityManager.createQuery(criteriaQuery.select(petTypeRoot).where(criteriaBuilder.equal(petTypeRoot.get("type"),name))).getResultList();
        return Optional.ofNullable(petTypes.size() == 0 ? null : petTypes.get(0));
    }

}
