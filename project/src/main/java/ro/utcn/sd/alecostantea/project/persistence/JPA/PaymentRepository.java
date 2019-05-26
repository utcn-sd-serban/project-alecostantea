package ro.utcn.sd.alecostantea.project.persistence.JPA;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alecostantea.project.model.Payment;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PaymentRepository {
    private final EntityManager entityManager;

    public Payment save(Payment payment) {
        if (payment.getId() == null) {
            entityManager.persist(payment);
            return payment;
        } else {
            return entityManager.merge(payment);
        }
    }

    public Optional<Payment> findByID(int id) {
        return Optional.ofNullable(entityManager.find(Payment.class, id));
    }

    public void remove(Payment payment) {
        entityManager.remove(payment);
    }

    public List<Payment> findALL() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Payment> criteriaQuery = criteriaBuilder.createQuery(Payment.class);
        criteriaQuery.select(criteriaQuery.from(Payment.class));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }


}
