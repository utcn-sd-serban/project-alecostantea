package ro.utcn.sd.alecostantea.project.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.utcn.sd.alecostantea.project.model.User;
import ro.utcn.sd.alecostantea.project.service.UserService;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserSeed implements CommandLineRunner {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userService.findAll().isEmpty()) {
            userService.save(new User(null, "ale", passwordEncoder.encode("sarmale"), null));
            userService.save(new User(null, "sunca", passwordEncoder.encode("feliata"), null));
        }
    }
}
