package ro.utcn.sd.alecostantea.project.persistence.JPA;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alecostantea.project.model.Room;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RoomRepository {
    private final EntityManager entityManager;

    public Room save(Room room) {
        if (room.getId() == null) {
            entityManager.persist(room);
            return room;
        } else {
            return entityManager.merge(room);
        }
    }

    public Optional<Room> findByID(int id) {
        return Optional.ofNullable(entityManager.find(Room.class, id));
    }

    public void remove(Room room) {
        entityManager.remove(room);
    }

    public List<Room> findALL() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);
        criteriaQuery.select(criteriaQuery.from(Room.class));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
