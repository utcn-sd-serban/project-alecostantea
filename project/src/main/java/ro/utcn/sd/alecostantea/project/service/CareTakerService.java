package ro.utcn.sd.alecostantea.project.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.sd.alecostantea.project.model.CareTaker;
import ro.utcn.sd.alecostantea.project.model.Payment;
import ro.utcn.sd.alecostantea.project.model.Pet;
import ro.utcn.sd.alecostantea.project.persistence.JPA.CareTakerRepository;
import ro.utcn.sd.alecostantea.project.persistence.JPA.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CareTakerService {
    private final RepositoryFactory factory;

    @Transactional
    public List<CareTaker> findAll() {
        CareTakerRepository repo = factory.createCareTakerRepository();
        return repo.findALL();
    }

    @Transactional
    public CareTaker save(CareTaker careTaker) {
        return factory.createCareTakerRepository().save(careTaker);
    }

    @Transactional
    public void remove(CareTaker careTaker) {
        factory.createCareTakerRepository().remove(careTaker);
    }

    @Transactional
    public CareTaker findById(int id) {
        return factory.createCareTakerRepository().findByID(id).get();
    }

    @Transactional
    public Optional<CareTaker> findByUsername(String careTakername) {
        return factory.createCareTakerRepository().findByUsername(careTakername);
    }

    @Transactional
    public List<Pet> getPetsToCareFor(CareTaker careTaker) {
        List<Pet> pets = factory.createPaymentRepository().findALL().stream().map(Payment::getPet).collect(Collectors.toList());
        pets = pets.stream().filter(pet -> careTaker.getCompatiblePets().contains(pet)).collect(Collectors.toList());
        return pets;
    }

}
