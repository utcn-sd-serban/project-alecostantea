package ro.utcn.sd.alecostantea.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPetDTO {
    private String username;
    private String petName;
    private String petType;
}
