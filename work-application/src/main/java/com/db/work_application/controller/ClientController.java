package com.db.work_application.controller;

import com.db.work_application.model.Client;
import com.db.work_application.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Validated
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable @Positive long id) {
        Client client = service.findById(id);
        if (client == null) throw new ClientNotFoundException(id);
        return client;
    }

    @GetMapping
    public List<Client> getAll() {
        return service.getAllClients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody @Valid Client client) {
        return service.createClient(client);
    }

    @GetMapping("/get-by-first-name-last-name/{firstName}/{lastName}")
    public List<Client> getClientsByFirstNameAndLastName(@PathVariable @NotEmpty String firstName, @PathVariable @NotEmpty String lastName) {
        return service.getClientsByFirstNameAndLastName(firstName, lastName);
    }
}
