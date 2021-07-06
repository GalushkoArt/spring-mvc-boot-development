package com.db.work_application.service;

import com.db.work_application.model.Client;

import java.util.List;

public interface ClientService {
    Client findById(long id);
    List<Client> getAllClients();
    Client createClient(long id, String firstName, String lastName);
}
