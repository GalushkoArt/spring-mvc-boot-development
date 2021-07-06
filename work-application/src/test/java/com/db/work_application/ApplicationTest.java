package com.db.work_application;

import com.db.work_application.config.JavaConfig;
import com.db.work_application.controller.ClientController;
import com.db.work_application.dao.ClientRepo;
import com.db.work_application.dao.ClientRepoStab;
import com.db.work_application.model.Client;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.List;

public class ApplicationTest {
    private ApplicationContext context;

    @Before
    public void init() {
        context = new AnnotationConfigApplicationContext(JavaConfig.class);
    }

    @Test
    public void smokeCheckTest() {
        Assert.assertNotNull(context);
    }

    @Test
    public void addClientTest() {
        ClientController controller = context.getBean(ClientController.class);
        controller.createClient("Petya", "Sidorov");

        Assert.assertEquals(controller.findById(1L).getFirstName(), "Petya");
        Assert.assertEquals(controller.findById(1L).getLastName(), "Sidorov");

        context.getBean(ClientRepo.class).removeById(1L);
    }

    @Test
    public void getAllClientsTest() {
        ClientController controller = context.getBean(ClientController.class);
        controller.createClient("Petya", "1");
        controller.createClient("Petya", "2");
        controller.createClient("Petya", "3");

        Assert.assertEquals(controller.getAll().size(), 3);

        context.getBean(ClientRepo.class).removeById(1L);
        context.getBean(ClientRepo.class).removeById(2L);
        context.getBean(ClientRepo.class).removeById(3L);
    }

    @Test
    public void getByIdTest() {
        context.getBean(ClientRepoStab.class).clientsMap.put(23L, new Client(23L, "Masha", "Ivanova"));
        ClientController controller = context.getBean(ClientController.class);

        Client client = controller.findById(23L);
        Assert.assertEquals(client.getId(), 23L);
        Assert.assertEquals(client.getFirstName(), "Masha");
        Assert.assertEquals(client.getLastName(), "Ivanova");

        context.getBean(ClientRepo.class).removeById(23L);
    }

    @Test
    public void getByFirstNameAndLastNameTest() {
        Client client1 = new Client(21L, "Masha", "Ivanova");
        Client client2 = new Client(23L, "Masha", "Ivanova");
        context.getBean(ClientRepoStab.class).clientsMap.put(21L, client1);
        context.getBean(ClientRepoStab.class).clientsMap.put(23L, client2);
        ClientController controller = context.getBean(ClientController.class);

        List<Client> clients = controller.getClientsByFirstNameAndLastName("Masha", "Ivanova");
        Assert.assertTrue(clients.containsAll(List.of(client1, client2)));

        context.getBean(ClientRepo.class).removeById(21L);
        context.getBean(ClientRepo.class).removeById(23L);
    }

    @After
    public void cleanUp() {
        context.getBean(ClientRepoStab.class).clientsMap = new HashMap<>();
    }
}
