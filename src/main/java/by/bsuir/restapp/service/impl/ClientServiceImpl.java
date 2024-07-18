package by.bsuir.restapp.service.impl;

import by.bsuir.restapp.exception.ResourceNotFoundException;
import by.bsuir.restapp.model.Client;
import by.bsuir.restapp.repository.ClientRepository;
import by.bsuir.restapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client isn't exists with clientId:" + clientId));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(Long clientId, Client updatedClient) {
        return clientRepository.findById(clientId)
                .map(client -> {
                    client.updateData(updatedClient);
                    return clientRepository.save(client);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client isn't exists with id:" + clientId));
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.findById(clientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client isn't exists with id:" + clientId));

        clientRepository.deleteById(clientId);
    }
}
