package com.db.work_application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Client {
    private long id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return "Client id: " + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'';
    }
}
