package com.db.work_application.service;

import com.db.work_application.dao.ClientRepo;
import com.db.work_application.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepo repository;

    public ClientServiceImpl(ClientRepo repository) {
        this.repository = repository;
    }

    @Override
    public Client findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return repository.getAll();
    }

    @Override
    public Client createClient(Client client) {
        if (client.getId() == 0) client.setId(repository.maxId() + 1);
        assert repository.saveClient(client) : "Can't save this client";
        return client;
    }

    @Override
    public List<Client> getClientsByFirstNameAndLastName(String firstName, String lastName) {
        return repository.getClientsByFirstNameAndLastName(firstName, lastName);
    }
}
