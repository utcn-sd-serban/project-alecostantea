package ro.utcn.sd.alecostantea.project.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.utcn.sd.alecostantea.project.model.PetType;
import ro.utcn.sd.alecostantea.project.service.PetTypeService;

@RequiredArgsConstructor
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PetTypeSeed implements CommandLineRunner {
    private final PetTypeService petTypeService;

    @Override
    public void run(String... args) throws Exception {

        if(petTypeService.findAll().isEmpty()){
            petTypeService.save(new PetType(null,"dog"));
            petTypeService.save(new PetType(null,"cat"));
        }
    }
}
