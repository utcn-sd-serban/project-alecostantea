package ro.utcn.sd.alecostantea.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.sd.alecostantea.project.model.Pet;
import ro.utcn.sd.alecostantea.project.model.User;
import ro.utcn.sd.alecostantea.project.persistence.JPA.RepositoryFactory;
import ro.utcn.sd.alecostantea.project.persistence.JPA.PetRepository;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PetService {
    private final RepositoryFactory factory;

    @Transactional
    public List<Pet> findAll() {
        PetRepository repo = factory.createPetRepository();
        return repo.findALL();
    }

    @Transactional
    public Pet save(Pet pet){
        return factory.createPetRepository().save(pet);
    }

    @Transactional
    public void remove(Pet pet){
        factory.createPetRepository().remove(pet);
    }

    @Transactional
    public Pet findById(int id)
    {
        return factory.createPetRepository().findByID(id).get();
    }

    @Transactional
    public List<Pet> findPetsOfUser(User user){
        return factory.createUserRepository().findByID(user.getId()).get().getPets();
    }

    @Transactional
    public Pet findByName(String name){
        return factory.createPetRepository().findByName(name).get();
    }

}
