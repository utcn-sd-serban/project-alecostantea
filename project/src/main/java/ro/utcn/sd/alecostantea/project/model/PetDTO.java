package ro.utcn.sd.alecostantea.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
    private String petName;
    private String petType;

    public static PetDTO ofEntity(Pet pet){
        PetDTO dto = new PetDTO();
        dto.setPetName(pet.getName());
        dto.setPetType(pet.getType().getType());
        return dto;
    }
}
