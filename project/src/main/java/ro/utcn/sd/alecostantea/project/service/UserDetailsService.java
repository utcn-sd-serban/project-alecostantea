package ro.utcn.sd.alecostantea.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.utcn.sd.alecostantea.project.model.CareTaker;
import ro.utcn.sd.alecostantea.project.model.User;
import ro.utcn.sd.alecostantea.project.persistence.JPA.RepositoryFactory;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final RepositoryFactory factory;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = factory.createUserRepository().findByUsername(s);
        if(user.isPresent()){
           return new org.springframework.security.core.userdetails.User(user.get().getUsername(),user.get().getPassword(),
                   Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        } else {
            Optional<CareTaker> careTaker = factory.createCareTakerRepository().findByUsername(s);
            if(careTaker.isPresent()){
                return new org.springframework.security.core.userdetails.User(careTaker.get().getUsername(),careTaker.get().getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_CARETAKER")));
            } else
            {
                throw new UsernameNotFoundException("Unknown user");
            }
        }
    }
}
