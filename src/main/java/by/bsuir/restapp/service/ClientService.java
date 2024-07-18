package by.bsuir.restapp.service;


import by.bsuir.restapp.model.Client;

import java.util.List;

public interface ClientService {
    Client createClient(Client client);

    Client getClientById(Long clientId);

    List<Client> getAllClients();

    Client updateClient(Long clientId, Client client);

    void deleteClient(Long clientId);
}
