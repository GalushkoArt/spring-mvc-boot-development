package com.db.work_application.controller;

import com.db.work_application.model.Client;
import com.db.work_application.service.ClientService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    public Client findById(long id) {
        return service.findById(id);
    }

    public List<Client> getAll() {
        return service.getAllClients();
    }

    public Client createClient(String firstName, String lastName) {
        return service.createClient(firstName, lastName);
    }

    public List<Client> getClientsByFirstNameAndLastName(String firstName, String lastName) {
        return service.getClientsByFirstNameAndLastName(firstName, lastName);
    }
}
