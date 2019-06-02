package ro.utcn.sd.alecostantea.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.sd.alecostantea.project.model.Payment;
import ro.utcn.sd.alecostantea.project.model.PaymentDTO;
import ro.utcn.sd.alecostantea.project.model.Room;
import ro.utcn.sd.alecostantea.project.model.UserDTO;
import ro.utcn.sd.alecostantea.project.persistence.JPA.PaymentRepository;
import ro.utcn.sd.alecostantea.project.persistence.JPA.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public PaymentDTO Book(PaymentDTO dto) {
        Payment payment = new Payment();
        payment.setPet(factory.createPetRepository().findByName(dto.getPetName()).get());
        payment.setUser(factory.createUserRepository().findByUsername(dto.getUsername()).get());
//        payment.setRoom(factory.createRoomRepository().findByID(dto.getRoomId()).get());

        List<Room> rooms = factory.createRoomRepository().findALL().stream().filter(room -> room.getSupportedPets().contains(payment.getPet().getType()))
                .collect(Collectors.toList());
        Collections.shuffle(rooms);
        payment.setRoom(rooms.get(0));
        payment.setPrice(dto.getPrice());

        return PaymentDTO.ofEntity(factory.createPaymentRepository().save(payment));
    }

    @Transactional
    public List<PaymentDTO> getBookings(UserDTO dto) {
        String username = dto.getUsername();
        List<Payment> payments = findAll();
        return payments.stream().filter(payment -> payment.getUser().getUsername().equals(username)).map(payment -> PaymentDTO.ofEntity(payment)).collect(Collectors.toList());
    }

}
