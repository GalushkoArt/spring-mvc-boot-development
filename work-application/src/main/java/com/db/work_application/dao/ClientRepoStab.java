package com.db.work_application.dao;

import com.db.work_application.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ClientRepoStab implements ClientRepo {
    private final Map<Long, Client> clientsMap = new HashMap<>();

    @Override
    public Client findById(long id) {
        return clientsMap.get(id);
    }

    @Override
    public List<Client> getAll() {
        return new ArrayList<>(clientsMap.values());
    }

    @Override
    public boolean saveClient(Client client) {
        return clientsMap.put(client.getId(), client) == null;
    }

    @Override
    public List<Client> getClientsByFirstNameAndLastName(String firstName, String lastName) {
        return clientsMap
                .values()
                .stream()
                .filter( client -> client.getFirstName().equals(firstName) && client.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public long maxId() {
        return clientsMap.keySet().stream().max(Long::compareTo).orElse(0L);
    }

    @Override
    public boolean removeById(Long id) {
        return clientsMap.remove(id) != null;
    }
}
