package ro.utcn.sd.alecostantea.project.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.utcn.sd.alecostantea.project.model.CareTaker;
import ro.utcn.sd.alecostantea.project.model.PetType;
import ro.utcn.sd.alecostantea.project.service.CareTakerService;
import ro.utcn.sd.alecostantea.project.service.PetTypeService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE+1)
public class CareTakerSeed implements CommandLineRunner {
    private final CareTakerService careTakerService;
    private final PetTypeService petTypeService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(careTakerService.findAll().isEmpty()){
            List<PetType> petTypes = petTypeService.findAll();
            List<PetType> petTypes2 = new ArrayList<PetType>();
            petTypes2.add(petTypeService.findByName("cat"));
            careTakerService.save(new CareTaker(null,"care1",passwordEncoder.encode("care1"),petTypes));
            careTakerService.save(new CareTaker(null,"care2",passwordEncoder.encode("care2"),petTypes2));
        }
    }
}
