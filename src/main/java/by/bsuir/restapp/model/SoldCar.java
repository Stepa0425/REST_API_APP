package by.bsuir.restapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sold_cars")
public class SoldCar implements Serializable {

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    public void updateData(SoldCar updatedSoldCar) {
        this.brand = updatedSoldCar.getBrand();
        this.model = updatedSoldCar.getModel();
        this.price = updatedSoldCar.getPrice();
    }
    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        SoldCar soldCar = (SoldCar) o;
        return id == soldCar.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}