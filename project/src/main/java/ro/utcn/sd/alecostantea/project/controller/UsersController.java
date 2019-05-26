package ro.utcn.sd.alecostantea.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.alecostantea.project.model.*;
import ro.utcn.sd.alecostantea.project.service.CareTakerService;
import ro.utcn.sd.alecostantea.project.service.RoomService;
import ro.utcn.sd.alecostantea.project.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    private final CareTakerService careTakerService;
    private final RoomService roomService;


    @GetMapping("/caretakers")
    public List<CareTaker> readAll() {
        return careTakerService.findAll();
    }

    @PostMapping("/caretakers")

    public CareTaker create(@RequestBody CareTaker careTaker) {
        return careTakerService.save(careTaker);
    }

    @GetMapping("/users")
    public List<User> readAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/rooms")
    public List<Room> readAllRooms() {
        return roomService.findAll();
    }

    @PutMapping("/login")
    public Response login(@RequestBody UserDTO dto) {
        return userService.login(dto);
    }

    @PutMapping("/user/pets")
    public List<Pet> getPets(@RequestBody UserDTO dto){
        return userService.getPets(dto);
    }

    @PostMapping("/user/pet")
    public Pet addPet(@RequestBody AddPetDTO dto){
        return userService.addPet(dto);
    }

}
