package ro.utcn.sd.alecostantea.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//this should rather be named booking because i use it as a booking mechanism but it has the same data as a payment
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn
    private User user;
    @OneToOne
    @JoinColumn
    private Pet pet;
    @OneToOne
    @JoinColumn
    private Room room;
    private Integer price;


    public void updatePrice(Integer price){
        this.price = this.price + price;
    }
}
