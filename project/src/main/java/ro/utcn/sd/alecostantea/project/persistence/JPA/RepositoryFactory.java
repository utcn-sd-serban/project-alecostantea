package ro.utcn.sd.alecostantea.project.persistence.JPA;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class RepositoryFactory {
    private final EntityManager entityManager;

    public UserRepository createUserRepository() {
        return new UserRepository(entityManager);
    }

    public PetRepository createPetRepository() {
        return new PetRepository(entityManager);
    }

    public PetTypeRepository createPetTypeRepository() {
        return new PetTypeRepository(entityManager);
    }

    public RoomRepository createRoomRepository() {
        return new RoomRepository(entityManager);
    }

    public CareTakerRepository createCareTakerRepository() {
        return new CareTakerRepository(entityManager);
    }

    public PaymentRepository createPaymentRepository() {
        return new PaymentRepository(entityManager);
    }
}
