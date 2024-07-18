package by.bsuir.restapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "clients")
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<SoldCar> soldCars = new ArrayList<>();


    public Client(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public void updateData(Client updatedClient){
        this.firstName = updatedClient.getFirstName();
        this.lastName = updatedClient.getLastName();
        this.email = updatedClient.getEmail();
        this.phone = updatedClient.getPhone();
    }

    public void addSoldCar(SoldCar soldCar) {
        soldCars.add(soldCar);
        soldCar.setClient(this);
    }

    public void removeSoldCar(SoldCar soldCar) {
        soldCars.remove(soldCar);
        soldCar.setClient(null);
    }
}
