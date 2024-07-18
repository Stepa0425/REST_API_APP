package by.bsuir.restapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "sold_cars")
public class SoldCar{

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

    public void updateData(SoldCar updatedSoldCar){
        this.brand = updatedSoldCar.getBrand();
        this.model = updatedSoldCar.getModel();
        this.price = updatedSoldCar.getPrice();
        this.client = updatedSoldCar.getClient();
    }
}