package by.bsuir.restapp;

import by.bsuir.restapp.controller.ClientController;
import by.bsuir.restapp.model.Client;
import by.bsuir.restapp.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;
    @Test
    void createClient() {
        Client newClient = new Client("John", "Doe", "john.doe@example.com", "1234567890");
        when(clientService.createClient(newClient)).thenReturn(newClient);

        ResponseEntity<Client> response = clientController.createClient(newClient);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newClient, response.getBody());
        verify(clientService, times(1)).createClient(newClient);
    }

    @Test
    void getClientById() {
        long clientId = 1L;
        Client client = new Client("John", "Doe", "john.doe@example.com", "1234567890");
        when(clientService.getClientById(clientId)).thenReturn(client);

        ResponseEntity<Client> response = clientController.getClientById(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(client, response.getBody());
        verify(clientService, times(1)).getClientById(clientId);
    }

    @Test
    void getAllClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("John", "Doe", "john.doe@example.com", "1234567890"));
        clients.add(new Client("Jane", "Doe", "jane.doe@example.com", "0987654321"));
        when(clientService.getAllClients()).thenReturn(clients);

        ResponseEntity<List<Client>> response = clientController.getAllClients();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clients, response.getBody());
        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void updateClient() {
        long clientId = 1L;
        Client updatedClient = new Client("John", "Doe", "john.doe@example.com", "9876543210");
        Client existingClient = new Client("John", "Doe", "john.doe@example.com", "1234567890");
        when(clientService.updateClient(clientId, updatedClient)).thenReturn(updatedClient);

        ResponseEntity<Client> response = clientController.updateClient(clientId, updatedClient);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedClient, response.getBody());
        verify(clientService, times(1)).updateClient(clientId, updatedClient);
    }

    @Test
    void deleteClient() {
        long clientId = 1L;

        ResponseEntity<String> response = clientController.deleteClient(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Client with id:" + clientId + " deleted successfully!", response.getBody());
        verify(clientService, times(1)).deleteClient(clientId);
    }
}