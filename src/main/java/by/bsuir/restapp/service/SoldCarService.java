package by.bsuir.restapp.service;

import by.bsuir.restapp.model.SoldCar;

import java.util.List;

public interface SoldCarService {
    SoldCar createSoldCar(SoldCar soldCar);

    SoldCar getSoldCarById(Long soldCarId);

    List<SoldCar> getAllSoldCars();

    SoldCar updateSoldCar(Long soldCarId, SoldCar soldCar);

    void deleteSoldCar(Long soldCarId);
}
