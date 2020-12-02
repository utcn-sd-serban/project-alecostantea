package ro.utcn.sd.alecostantea.project.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.utcn.sd.alecostantea.project.model.PetType;
import ro.utcn.sd.alecostantea.project.model.Room;
import ro.utcn.sd.alecostantea.project.service.PetTypeService;
import ro.utcn.sd.alecostantea.project.service.RoomService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RoomSeed implements CommandLineRunner {
    private final RoomService roomService;
    private final PetTypeService petTypeService;

    @Override

    public void run(String... args) throws Exception {

        if (roomService.findAll().isEmpty()) {
            List<PetType> supportedPets = petTypeService.findAll();
            List<PetType> petTypes = new ArrayList<>();
            petTypes.add(supportedPets.get(0));
            roomService.save(new Room(null, supportedPets));
            roomService.save(new Room(null,petTypes));
            petTypes = new ArrayList<>();
            petTypes.add(supportedPets.get(1));
            roomService.save(new Room(null,petTypes));

        }
    }
}
