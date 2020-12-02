package ro.utcn.sd.alecostantea.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    @OneToMany
    @JoinColumn
    private List<Pet> pets;

    public void addPet(Pet pet){
        pets.add(pet);
    }
}
