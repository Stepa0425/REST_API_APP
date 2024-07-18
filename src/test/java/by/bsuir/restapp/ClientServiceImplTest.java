package by.bsuir.restapp;

import by.bsuir.restapp.exception.ResourceNotFoundException;
import by.bsuir.restapp.model.Client;
import by.bsuir.restapp.repository.ClientRepository;
import by.bsuir.restapp.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void testCreateClient() {
        Client client = new Client();
        when(clientRepository.save(client)).thenReturn(client);
        Client createdClient = clientService.createClient(client);

        assertNotNull(createdClient);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testGetClientByIdWhenClientExists() {
        Long clientId = 1L;
        Client client = new Client();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        Client foundClient = clientService.getClientById(clientId);

        assertNotNull(foundClient);
        assertEquals(client, foundClient);
    }

    @Test
    void testGetClientByIdWhenClientNotExists() {
        Long clientId = 1L;
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            clientService.getClientById(clientId);
        });

        String expectedMessage = "Client isn't exists with clientId:" + clientId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetAllClients() {
        Client client1 = new Client();
        Client client2 = new Client();
        List<Client> clients = Arrays.asList(client1, client2);
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> allClients = clientService.getAllClients();

        assertEquals(2, allClients.size());
        assertTrue(allClients.contains(client1));
        assertTrue(allClients.contains(client2));
    }

    @Test
    void testUpdateClientWhenClientExists() {
        Long clientId = 1L;
        Client existingClient = new Client();
        Client updatedClient = new Client();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(existingClient);

        Client result = clientService.updateClient(clientId, updatedClient);

        verify(clientRepository, times(1)).save(existingClient);
        assertNotNull(result);
    }

    @Test
    void testUpdateClientWhenClientNotExists() {
        Long clientId = 1L;
        Client updatedClient = new Client();
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            clientService.updateClient(clientId, updatedClient);
        });

        String expectedMessage = "Client isn't exists with id:" + clientId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDeleteClientWhenClientExists() {
        Long clientId = 1L;
        Client client = new Client();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        clientService.deleteClient(clientId);

        verify(clientRepository, times(1)).deleteById(clientId);
    }

    @Test
    void testDeleteClientWhenClientNotExists() {
        Long clientId = 1L;
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            clientService.deleteClient(clientId);
        });

        String expectedMessage = "Client isn't exists with id:" + clientId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
