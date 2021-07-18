package com.db.work_application;

import com.db.work_application.config.JavaConfig;
import com.db.work_application.dao.ClientRepo;
import com.db.work_application.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(classes = JavaConfig.class)
public class ClientTest {
    private MockMvc mockMvc;
    @Autowired
    private ClientRepo repo;

    @BeforeEach
    public void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getAllClientsWhenNoEntriesTest() throws Exception {
        mockMvc.perform(get("/api/clients/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void getClientByIdWhenNoEntryWithIdTest() throws Exception {
        mockMvc.perform(get("/api/clients/12"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createClientTest() throws Exception {
        mockMvc.perform(post("/api/clients")
                .content(Client.builder().firstName("John").lastName("Ivanovich").build().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json("{firstName: John, lastName: Ivanovich, id: 1}"));

        repo.removeById(1L);
    }

    @Test
    public void createClientAndGetIdTest() throws Exception {
        mockMvc.perform(post("/api/clients")
                .content(Client.builder().firstName("John").lastName("Ivanovich").build().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json("{firstName: John, lastName: Ivanovich, id: 1}"));

        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{firstName: John, lastName: Ivanovich, id: 1}"));

        repo.removeById(1L);
    }

    @Test
    public void getClientByExistingIdTest() throws Exception {
        Client client = Client.builder().id(15).firstName("Peter").lastName("Danillin").build();
        repo.saveClient(client);

        mockMvc.perform(get("/api/clients/15"))
                .andExpect(status().isOk())
                .andExpect(content().json(client.toString()));

        repo.removeById(15L);
    }

    @Test
    public void getAllClientsNotEmptyTest() throws Exception {
        Client client1 = Client.builder().id(1).firstName("Peter").lastName("Danillin").build();
        Client client2 = Client.builder().id(2).firstName("Peter").lastName("Sashov").build();
        repo.saveClient(client1);
        repo.saveClient(client2);

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(content().json(List.of(client1, client2).toString()));

        repo.removeById(1L);
        repo.removeById(2L);
    }
}
