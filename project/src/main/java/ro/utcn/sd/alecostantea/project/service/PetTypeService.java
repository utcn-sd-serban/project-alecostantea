package ro.utcn.sd.alecostantea.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.sd.alecostantea.project.model.PetType;
import ro.utcn.sd.alecostantea.project.persistence.JPA.PetTypeRepository;
import ro.utcn.sd.alecostantea.project.persistence.JPA.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PetTypeService {
    private final RepositoryFactory factory;

    @Transactional
    public List<PetType> findAll() {
        PetTypeRepository repo = factory.createPetTypeRepository();
        return repo.findALL();
    }

    @Transactional
    public PetType save(PetType petType) {
        return factory.createPetTypeRepository().save(petType);
    }

    @Transactional
    public void remove(PetType petType) {
        factory.createPetTypeRepository().remove(petType);
    }

    @Transactional
    public PetType findById(int id) {
        return factory.createPetTypeRepository().findByID(id).get();
    }

    @Transactional
    public PetType findByName(String name) {
        return factory.createPetTypeRepository().findByName(name).get();
    }


}
