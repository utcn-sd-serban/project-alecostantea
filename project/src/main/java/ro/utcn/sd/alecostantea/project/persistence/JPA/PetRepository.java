package ro.utcn.sd.alecostantea.project.persistence.JPA;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alecostantea.project.model.Pet;
import ro.utcn.sd.alecostantea.project.model.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PetRepository {
    private final EntityManager entityManager;

    public Pet save(Pet pet) {
        if (pet.getId() == null) {
            entityManager.persist(pet);
            return pet;
        } else {
            return entityManager.merge(pet);
        }
    }

    public Optional<Pet> findByID(int id) {
        return Optional.ofNullable(entityManager.find(Pet.class, id));
    }

    public void remove(Pet pet) {
        entityManager.remove(pet);
    }

    public List<Pet> findALL() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pet> criteriaQuery = criteriaBuilder.createQuery(Pet.class);
        criteriaQuery.select(criteriaQuery.from(Pet.class));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


}
