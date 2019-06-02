package ro.utcn.sd.alecostantea.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.utcn.sd.alecostantea.project.model.*;
import ro.utcn.sd.alecostantea.project.persistence.JPA.RepositoryFactory;
import ro.utcn.sd.alecostantea.project.persistence.JPA.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RepositoryFactory factory;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public List<User> findAll() {
        UserRepository repo = factory.createUserRepository();
        return repo.findALL();
    }

    @Transactional
    public User save(User user) {
        return factory.createUserRepository().save(user);
    }

    @Transactional
    public void remove(User user) {
        factory.createUserRepository().remove(user);
    }

    @Transactional
    public User findById(int id) {
        return factory.createUserRepository().findByID(id).get();
    }

    public Optional<User> findByUsername(String username) {
        return factory.createUserRepository().findByUsername(username);
    }

    @Transactional
    public void addPet(User user, Pet pet) {
        user.addPet(pet);
    }

    @Transactional
    public List<Pet> getPetsOfUser(User user) {
        return user.getPets();
    }

    @Transactional
    public List<Pet> getPetsOfUser(String username) {

        Optional<User> user = findByUsername(username);
        if (user.isPresent()) {
            return user.get().getPets();
        } else {
            throw new UsernameNotFoundException("Cannot get pets of unknown user");
        }
    }

    @Transactional
    public List<Pet> getPetsOfUser(int id) {
        return findById(id).getPets();
    }

    @Transactional
    public Response login(UserDTO dto) {
        Optional<User> user = findByUsername(dto.getUsername());
        Response response = new Response();
        if (user.isPresent()) {
            if (passwordEncoder.matches(dto.getPassword(),user.get().getPassword())) {
                response.setResponse("user");
            } else {

                System.out.println("actual password:" + user.get().getPassword() + " wrong pass: " + passwordEncoder.encode(dto.getPassword()) + " unencoded: " + dto.getPassword());
                response.setResponse("wrong password");
            }
        } else {
            Optional<CareTaker> careTaker = factory.createCareTakerRepository().findByUsername(dto.getUsername());
            if (careTaker.isPresent()) {
                if (passwordEncoder.matches(dto.getPassword(),careTaker.get().getPassword())) {
                    response.setResponse("caretaker");
                } else {
                    System.out.println("actual password:" + careTaker.get().getPassword() + " wrong pass: " + passwordEncoder.encode(dto.getPassword()) + " unencoded: " + dto.getPassword());

                    response.setResponse("wrong password");

                }
            } else {
                response.setResponse("user unknown");
            }
        }
        return response;
    }

    @Transactional
    public List<PetDTO> getPets(UserDTO dto){
        User user = factory.createUserRepository().findByUsername(dto.getUsername()).get();
        return user.getPets().stream().map(pet -> PetDTO.ofEntity(
                pet
        )).collect(Collectors.toList());
    }
    @Transactional
    public Pet addPet(AddPetDTO dto){
        User user = factory.createUserRepository().findByUsername(dto.getUsername()).get();
        PetType petType = factory.createPetTypeRepository().findByName(dto.getPetType()).get();
        Pet pet = new Pet(null,dto.getPetName(),petType,user);
        pet = factory.createPetRepository().save(pet);
        user.addPet(pet);
        user = factory.createUserRepository().save(user);
        return pet;
    }
}
