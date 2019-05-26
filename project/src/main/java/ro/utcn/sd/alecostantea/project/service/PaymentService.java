package ro.utcn.sd.alecostantea.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.sd.alecostantea.project.model.Payment;
import ro.utcn.sd.alecostantea.project.persistence.JPA.PaymentRepository;
import ro.utcn.sd.alecostantea.project.persistence.JPA.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final RepositoryFactory factory;

    @Transactional
    public List<Payment> findAll() {
        PaymentRepository repo = factory.createPaymentRepository();
        return repo.findALL();
    }

    @Transactional
    public Payment save(Payment payment) {
        return factory.createPaymentRepository().save(payment);
    }

    @Transactional
    public void remove(Payment payment) {
        factory.createPaymentRepository().remove(payment);
    }

    @Transactional
    public Payment findById(int id) {
        return factory.createPaymentRepository().findByID(id).get();
    }

}
