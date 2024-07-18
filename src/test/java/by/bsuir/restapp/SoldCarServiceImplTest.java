package by.bsuir.restapp;

import by.bsuir.restapp.model.Client;
import by.bsuir.restapp.model.SoldCar;
import by.bsuir.restapp.repository.SoldCarRepository;
import by.bsuir.restapp.service.impl.SoldCarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SoldCarServiceImplTest {

    @Mock
    private SoldCarRepository soldCarRepository;

    @InjectMocks
    private SoldCarServiceImpl soldCarService;

    @Test
    void testCreateSoldCar() {
        Client client = new Client("John", "Doe", "john.doe@example.com", "123456789");
        SoldCar soldCar = new SoldCar("Toyota", "Camry", new BigDecimal(25000), client);

        when(soldCarRepository.save(any(SoldCar.class))).thenReturn(soldCar);

        SoldCar createdSoldCar = soldCarService.createSoldCar(soldCar);

        assertNotNull(createdSoldCar);
        assertEquals("Toyota", createdSoldCar.getBrand());
        assertEquals("Camry", createdSoldCar.getModel());
        assertEquals(new BigDecimal(25000), createdSoldCar.getPrice());
        assertEquals(client, createdSoldCar.getClient());
        verify(soldCarRepository, times(1)).save(any(SoldCar.class));
    }

    @Test
    void testGetSoldCarById() {
        Long soldCarId = 1L;
        Client client = new Client("John", "Doe", "john.doe@example.com", "123456789");
        SoldCar soldCar = new SoldCar("Toyota", "Camry", new BigDecimal(25000), client);

        when(soldCarRepository.findById(soldCarId)).thenReturn(Optional.of(soldCar));

        SoldCar fetchedSoldCar = soldCarService.getSoldCarById(soldCarId);

        assertNotNull(fetchedSoldCar);
        assertEquals("Toyota", fetchedSoldCar.getBrand());
        assertEquals("Camry", fetchedSoldCar.getModel());
        assertEquals(new BigDecimal(25000), fetchedSoldCar.getPrice());
        assertEquals(client, fetchedSoldCar.getClient());
        verify(soldCarRepository, times(1)).findById(soldCarId);
    }

    @Test
    void testUpdateSoldCar() {
        Long soldCarId = 1L;
        Client client = new Client("John", "Doe", "john.doe@example.com", "123456789");
        SoldCar existingSoldCar = new SoldCar("Toyota", "Camry", new BigDecimal(25000), client);
        SoldCar updatedSoldCar = new SoldCar("Honda", "Civic", new BigDecimal(20000), client);

        when(soldCarRepository.findById(soldCarId)).thenReturn(Optional.of(existingSoldCar));
        when(soldCarRepository.save(any(SoldCar.class))).thenReturn(updatedSoldCar);

        SoldCar result = soldCarService.updateSoldCar(soldCarId, updatedSoldCar);

        assertNotNull(result);
        assertEquals("Honda", result.getBrand());
        assertEquals("Civic", result.getModel());
        assertEquals(new BigDecimal(20000), result.getPrice());
        assertEquals(client, result.getClient());
        verify(soldCarRepository, times(1)).findById(soldCarId);
        verify(soldCarRepository, times(1)).save(any(SoldCar.class));
    }

    @Test
    void testDeleteSoldCar() {
        Long soldCarId = 1L;
        Client client = new Client("John", "Doe", "john.doe@example.com", "123456789");
        SoldCar soldCar = new SoldCar("Toyota", "Camry", new BigDecimal(25000), client);

        when(soldCarRepository.findById(soldCarId)).thenReturn(Optional.of(soldCar));
        doNothing().when(soldCarRepository).deleteById(soldCarId);

        soldCarService.deleteSoldCar(soldCarId);

        verify(soldCarRepository, times(1)).findById(soldCarId);
        verify(soldCarRepository, times(1)).deleteById(soldCarId);
    }
}