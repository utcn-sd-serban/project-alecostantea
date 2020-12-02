package ro.utcn.sd.alecostantea.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.alecostantea.project.model.*;
import ro.utcn.sd.alecostantea.project.service.CareTakerService;
import ro.utcn.sd.alecostantea.project.service.PaymentService;
import ro.utcn.sd.alecostantea.project.service.RoomService;
import ro.utcn.sd.alecostantea.project.service.UserService;

import javax.swing.text.Caret;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    private final CareTakerService careTakerService;
    private final RoomService roomService;
    private final PaymentService paymentService;


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
    public List<PetDTO> getPets(@RequestBody UserDTO dto){
        return userService.getPets(dto);
    }

    @PostMapping("/user/pet")
    public PetDTO addPet(@RequestBody AddPetDTO dto){
        return PetDTO.ofEntity(userService.addPet(dto));
    }

    @PutMapping("/book")
    public PaymentDTO book(@RequestBody PaymentDTO dto){
        return paymentService.Book(dto);
    }

    @PutMapping("/bookings")
    public List<PaymentDTO> getBookings(@RequestBody UserDTO dto){
        return paymentService.getBookings(dto);
    }

    @PutMapping("/caretaker/bookings")
    public List<PaymentDTO> getPetsToCareFor(@RequestBody UserDTO dto){
        CareTaker careTaker = careTakerService.findByUsername(dto.getUsername()).get();
        return paymentService.findAll().stream()
                .filter(payment -> careTaker.getCompatiblePets().contains(payment.getPet().getType()))
                .map(payment -> PaymentDTO.ofEntity(payment))
                .collect(Collectors.toList());
    }

    @PostMapping("/pet/care")
    public Response carePet(@RequestBody PaymentDTO dto){
        Response response = new Response();
        Payment payment = paymentService.findById(dto.getId());
        payment.updatePrice(dto.getPrice());
        payment = paymentService.save(payment);
        response.setResponse("ok");
        return response;
    }


    @PostMapping("/checkout")
    public Response checkout(@RequestBody PaymentDTO dto){
        Payment payment = paymentService.findById(dto.getId());
        paymentService.remove(payment);
        return new Response("ok");
    }

}
