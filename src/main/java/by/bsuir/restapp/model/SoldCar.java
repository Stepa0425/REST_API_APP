package by.bsuir.restapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sold_cars")
public class SoldCar extends CoreModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "price")
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public SoldCar(String brand, String model, BigDecimal price, Client client) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.client = client;
    }

    public SoldCar(List<Error> errors) {
        super(errors);
    }
}