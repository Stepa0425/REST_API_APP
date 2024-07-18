package by.bsuir.restapp.controller;

import by.bsuir.restapp.model.SoldCar;
import by.bsuir.restapp.service.ClientService;
import by.bsuir.restapp.service.SoldCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test/app")
public class SoldCarController {
    private SoldCarService soldCarService;
    private ClientService clientService;

    @Autowired
    public void setSoldCarService(SoldCarService soldCarService, ClientService clientService) {
        this.soldCarService = soldCarService;
        this.clientService = clientService;
    }

    @PostMapping("/clients/{idClient}/soldCars")
    public ResponseEntity<SoldCar> createSoldCar(@RequestBody SoldCar soldCar, @PathVariable Long idClient) {
        clientService.getClientById(idClient).addSoldCar(soldCar);
        SoldCar savedSoldCar = soldCarService.createSoldCar(soldCar);
        return new ResponseEntity<>(savedSoldCar, HttpStatus.CREATED);
    }

    @GetMapping("/soldCars/{id}")
    public ResponseEntity<SoldCar> getSoldCarById(@PathVariable Long id) {
        return ResponseEntity.ok(soldCarService.getSoldCarById(id));
    }

    @GetMapping("/soldCars")
    public ResponseEntity<List<SoldCar>> getAllSoldCars() {
        return ResponseEntity.ok(soldCarService.getAllSoldCars());
    }

    @PutMapping("/soldCars/{idSoldCar}")
    public ResponseEntity<SoldCar> updateSoldCar(@PathVariable Long idSoldCar, @RequestBody SoldCar updatedSoldCar) {
        return ResponseEntity.ok(soldCarService.updateSoldCar(idSoldCar, updatedSoldCar));
    }

    @DeleteMapping("/clients/{idClient}/soldCars/{idSoldCar}")
    public ResponseEntity<String> deleteSoldCar(@PathVariable Long idSoldCar, @PathVariable Long idClient) {
        clientService.getClientById(idClient).removeSoldCar(soldCarService.getSoldCarById(idSoldCar));
        soldCarService.deleteSoldCar(idSoldCar);
        return ResponseEntity.ok("Client with id:" + idSoldCar + " deleted successfully!");
    }
}
