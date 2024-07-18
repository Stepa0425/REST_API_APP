package by.bsuir.restapp.repository;

import by.bsuir.restapp.model.SoldCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldCarRepository extends JpaRepository<SoldCar, Long> {
}
