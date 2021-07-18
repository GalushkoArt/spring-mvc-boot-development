package com.db.work_application.model;

import lombok.*;

import static com.db.work_application.utils.ObjectUtil.asJsonString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private long id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return asJsonString(this);
    }
}
