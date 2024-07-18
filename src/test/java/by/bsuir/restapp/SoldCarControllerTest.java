package by.bsuir.restapp;

import by.bsuir.restapp.controller.SoldCarController;
import by.bsuir.restapp.model.Client;
import by.bsuir.restapp.model.SoldCar;
import by.bsuir.restapp.service.ClientService;
import by.bsuir.restapp.service.SoldCarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SoldCarControllerTest {

    @Mock
    private SoldCarService soldCarService;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private SoldCarController soldCarController;
    @Test
    void testCreateSoldCar() {
        SoldCar soldCar = new SoldCar();
        soldCar.setBrand("Toyota");
        soldCar.setModel("Camry");
        soldCar.setPrice(BigDecimal.valueOf(25000.0));
        Client client = new Client();
        when(clientService.getClientById(anyLong())).thenReturn(client);
        when(soldCarService.createSoldCar(any(SoldCar.class))).thenReturn(soldCar);

        ResponseEntity<SoldCar> response = soldCarController.createSoldCar(soldCar, 1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(soldCar);
        verify(clientService).getClientById(1L);
        verify(soldCarService).createSoldCar(soldCar);
    }

    @Test
    void testGetSoldCarById() {
        SoldCar soldCar = new SoldCar();
        soldCar.setId(1L);
        when(soldCarService.getSoldCarById(1L)).thenReturn(soldCar);

        ResponseEntity<SoldCar> response = soldCarController.getSoldCarById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(soldCar);
        verify(soldCarService).getSoldCarById(1L);
    }

    @Test
    void testGetAllSoldCars() {
        List<SoldCar> soldCars = new ArrayList<>();
        soldCars.add(new SoldCar());
        soldCars.add(new SoldCar());
        when(soldCarService.getAllSoldCars()).thenReturn(soldCars);

        ResponseEntity<List<SoldCar>> response = soldCarController.getAllSoldCars();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(soldCars);
        verify(soldCarService).getAllSoldCars();
    }

    @Test
    void testUpdateSoldCar() {
        SoldCar soldCar = new SoldCar();
        soldCar.setId(1L);
        soldCar.setBrand("Toyota");
        soldCar.setModel("Camry");
        soldCar.setPrice(BigDecimal.valueOf(25000.0));
        SoldCar updatedSoldCar = new SoldCar();
        updatedSoldCar.setBrand("Honda");
        updatedSoldCar.setModel("Civic");
        updatedSoldCar.setPrice(BigDecimal.valueOf(22000.0));
        when(soldCarService.updateSoldCar(1L, updatedSoldCar)).thenReturn(updatedSoldCar);

        ResponseEntity<SoldCar> response = soldCarController.updateSoldCar(1L, updatedSoldCar);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(updatedSoldCar);
        verify(soldCarService).updateSoldCar(1L, updatedSoldCar);
    }

    @Test
    void testDeleteSoldCar() {
        SoldCar soldCar = new SoldCar();
        soldCar.setId(1L);
        Client client = new Client();
        when(clientService.getClientById(anyLong())).thenReturn(client);
        when(soldCarService.getSoldCarById(1L)).thenReturn(soldCar);

        ResponseEntity<String> response = soldCarController.deleteSoldCar(1L, 1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Client with id:1 deleted successfully!");
        verify(clientService).getClientById(1L);
        verify(soldCarService).getSoldCarById(1L);
        verify(soldCarService).deleteSoldCar(1L);
    }
}