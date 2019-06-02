package ro.utcn.sd.alecostantea.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Integer id;
    private String username;
    private String petName;
    private int roomId;
    private int price;

    public static PaymentDTO ofEntity(Payment payment){
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setPetName(payment.getPet().getName());
        dto.setRoomId(payment.getRoom().getId());
        dto.setUsername(payment.getUser().getUsername());
        dto.setPrice(payment.getPrice());
        return dto;

    }
}
