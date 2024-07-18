package by.bsuir.restapp;

import by.bsuir.restapp.exception.ResourceNotFoundException;
import by.bsuir.restapp.model.SoldCar;
import by.bsuir.restapp.repository.SoldCarRepository;
import by.bsuir.restapp.service.impl.SoldCarServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SoldCarServiceImplTest {
    @Mock
    private SoldCarRepository soldCarRepository;
    @InjectMocks
    private SoldCarServiceImpl soldCarService;

    @Test
    void testCreateSoldCar() {
        SoldCar soldCar = new SoldCar();
        soldCar.setBrand("Toyota");
        soldCar.setModel("Camry");
        soldCar.setPrice(new BigDecimal(25000.00));

        Mockito.when(soldCarRepository.save(soldCar)).thenReturn(soldCar);

        SoldCar createdSoldCar = soldCarService.createSoldCar(soldCar);

        Assertions.assertNotNull(createdSoldCar);
        Assertions.assertEquals("Toyota", createdSoldCar.getBrand());
        Assertions.assertEquals("Camry", createdSoldCar.getModel());
        Assertions.assertEquals(new BigDecimal(25000.00), createdSoldCar.getPrice());
    }

    @Test
    void testGetSoldCarById() {
        Long soldCarId = 1L;
        SoldCar soldCar = new SoldCar();
        soldCar.setId(soldCarId);
        soldCar.setBrand("Toyota");
        soldCar.setModel("Camry");
        soldCar.setPrice(new BigDecimal(25000.00));

        Mockito.when(soldCarRepository.findById(soldCarId)).thenReturn(Optional.of(soldCar));

        SoldCar foundSoldCar = soldCarService.getSoldCarById(soldCarId);

        Assertions.assertNotNull(foundSoldCar);
        Assertions.assertEquals(soldCarId, foundSoldCar.getId());
        Assertions.assertEquals("Toyota", foundSoldCar.getBrand());
        Assertions.assertEquals("Camry", foundSoldCar.getModel());
        Assertions.assertEquals(new BigDecimal(25000.00), foundSoldCar.getPrice());
    }

    @Test
    void testGetSoldCarByIdNotFound() {
        Long soldCarId = 1L;
        Mockito.when(soldCarRepository.findById(soldCarId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> soldCarService.getSoldCarById(soldCarId));
    }

    @Test
    void testGetAllSoldCars() {
        List<SoldCar> soldCars = new ArrayList<>();
        SoldCar soldCar1 = new SoldCar();
        soldCar1.setId(1L);
        soldCar1.setBrand("Toyota");
        soldCar1.setModel("Camry");
        soldCar1.setPrice(new BigDecimal(25000.00));

        SoldCar soldCar2 = new SoldCar();
        soldCar2.setId(2L);
        soldCar2.setBrand("Honda");
        soldCar2.setModel("Civic");
        soldCar2.setPrice(new BigDecimal(20000.00));

        soldCars.add(soldCar1);
        soldCars.add(soldCar2);

        Mockito.when(soldCarRepository.findAll()).thenReturn(soldCars);

        List<SoldCar> allSoldCars = soldCarService.getAllSoldCars();

        Assertions.assertNotNull(allSoldCars);
        Assertions.assertEquals(2, allSoldCars.size());
        Assertions.assertEquals("Toyota", allSoldCars.get(0).getBrand());
        Assertions.assertEquals("Honda", allSoldCars.get(1).getBrand());
    }

    @Test
    void testUpdateSoldCar() {
        Long soldCarId = 1L;
        SoldCar existingSoldCar = new SoldCar();
        existingSoldCar.setId(soldCarId);
        existingSoldCar.setBrand("Toyota");
        existingSoldCar.setModel("Camry");
        existingSoldCar.setPrice(new BigDecimal(25000.00));

        SoldCar updatedSoldCar = new SoldCar();
        updatedSoldCar.setBrand("Honda");
        updatedSoldCar.setModel("Civic");
        updatedSoldCar.setPrice(new BigDecimal(22000.00));

        Mockito.when(soldCarRepository.findById(soldCarId)).thenReturn(Optional.of(existingSoldCar));
        Mockito.when(soldCarRepository.save(existingSoldCar)).thenReturn(existingSoldCar);

        SoldCar updatedCar = soldCarService.updateSoldCar(soldCarId, updatedSoldCar);

        Assertions.assertNotNull(updatedCar);
        Assertions.assertEquals("Honda", updatedCar.getBrand());
        Assertions.assertEquals("Civic", updatedCar.getModel());
        Assertions.assertEquals(new BigDecimal(22000.00), updatedCar.getPrice());
    }

    @Test
    void testUpdateSoldCarNotFound() {
        Long soldCarId = 1L;
        SoldCar updatedSoldCar = new SoldCar();
        updatedSoldCar.setBrand("Honda");
        updatedSoldCar.setModel("Civic");
        updatedSoldCar.setPrice(new BigDecimal(22000.00));

        Mockito.when(soldCarRepository.findById(soldCarId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> soldCarService.updateSoldCar(soldCarId, updatedSoldCar));
    }

    @Test
    void testDeleteSoldCar() {
        Long soldCarId = 1L;
        SoldCar soldCar = new SoldCar();
        soldCar.setId(soldCarId);
        soldCar.setBrand("Toyota");
        soldCar.setModel("Camry");
        soldCar.setPrice(new BigDecimal(25000.00));

        Mockito.when(soldCarRepository.findById(soldCarId)).thenReturn(Optional.of(soldCar));
        Mockito.doNothing().when(soldCarRepository).deleteById(soldCarId);

        soldCarService.deleteSoldCar(soldCarId);

        Mockito.verify(soldCarRepository, Mockito.times(1)).deleteById(soldCarId);
    }

    @Test
    void testDeleteSoldCarNotFound() {
        // Arrange
        Long soldCarId = 1L;
        Mockito.when(soldCarRepository.findById(soldCarId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> soldCarService.deleteSoldCar(soldCarId));
    }
}