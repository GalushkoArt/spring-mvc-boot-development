package com.db.work_application.controller;

import com.db.work_application.model.Client;
import com.db.work_application.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Client> getAll() {
        return service.getAllClients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody String firstName, @RequestBody String lastName) {
        return service.createClient(firstName, lastName);
    }

    @GetMapping("/get-by-first-name-last-name?firstName={firstName}&lastName={lastName}")
    public @ResponseBody List<Client> getClientsByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        return service.getClientsByFirstNameAndLastName(firstName, lastName);
    }
}
