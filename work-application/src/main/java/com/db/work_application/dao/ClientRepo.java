package com.db.work_application.dao;

import com.db.work_application.model.Client;

import java.util.List;

public interface ClientRepo {
    Client findById(long id);
    List<Client> getAll();
    boolean saveClient(Client client);
}
