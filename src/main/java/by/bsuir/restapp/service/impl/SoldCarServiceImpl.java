package by.bsuir.restapp.service.impl;

import by.bsuir.restapp.exception.ResourceNotFoundException;
import by.bsuir.restapp.model.SoldCar;
import by.bsuir.restapp.repository.SoldCarRepository;
import by.bsuir.restapp.service.SoldCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoldCarServiceImpl implements SoldCarService {
    private final SoldCarRepository soldCarRepository;

    @Override
    public SoldCar createSoldCar(SoldCar soldCar) {
        if (soldCar.getClient() == null) {
            throw new RuntimeException("The client should exists!");
        }
        return soldCarRepository.save(soldCar);
    }

    @Override
    public SoldCar getSoldCarById(Long soldCarId) {
        return soldCarRepository.findById(soldCarId)
                .orElseThrow(() -> new ResourceNotFoundException("Sold car isn't exists with soldCarId:" + soldCarId)
                );
    }

    @Override
    public List<SoldCar> getAllSoldCars() {
        return soldCarRepository.findAll();
    }

    @Override
    public SoldCar updateSoldCar(Long soldCarId, SoldCar updatedSoldCar) {
        SoldCar soldCar = soldCarRepository.findById(soldCarId)
                .orElseThrow(() -> new ResourceNotFoundException("Sold car isn't exists with soldCarId:" + soldCarId));
        soldCar.updateData(updatedSoldCar);
        return soldCarRepository.save(soldCar);
    }

    @Override
    public void deleteSoldCar(Long soldCarId) {
        soldCarRepository.findById(soldCarId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sold car isn't exists with id:" + soldCarId));

        soldCarRepository.deleteById(soldCarId);
    }
}
