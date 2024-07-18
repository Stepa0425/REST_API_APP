package by.bsuir.restapp;
import by.bsuir.restapp.controller.ClientController;
import by.bsuir.restapp.model.Client;
import by.bsuir.restapp.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService clientService;

    @Test
    void createUser() throws Exception {
        Client newClient = new Client(null, "John","Doe", "john@example.com", "375295132437");
        Client savedClient = new Client(1L, "John","Doe", "john@example.com", "375295132437");

        when(clientService.createClient(any(Client.class))).thenReturn(savedClient);

        mockMvc.perform(MockMvcRequestBuilders.post("/test/app/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newClient)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("375295132437"));

        verify(clientService, times(1)).createClient(any(Client.class));
    }

    @Test
    void getClientById() throws Exception {
        Client client = new Client(1L, "John","Doe", "john@example.com", "375295132437");

        when(clientService.getClientById(1L)).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.get("/test/app/clients/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("375295132437"));

        verify(clientService, times(1)).getClientById(1L);
    }

    @Test
    void getAllClients() throws Exception {
        List<Client> clients = Arrays.asList(
                new Client(1L, "John","Doe", "john@example.com", "375295132437"),
                new Client(2L, "Jane","Doe", "jane@example.com", "375295132438")
        );

        when(clientService.getAllClients()).thenReturn(clients);

        mockMvc.perform(MockMvcRequestBuilders.get("/test/app/clients"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("john@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phone").value("375295132437"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phone").value("375295132438"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("jane@example.com"));

        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void deleteClient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/test/app/clients/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Client with id:1 deleted successfully!"));

        verify(clientService, times(1)).deleteClient(1L);
    }
}