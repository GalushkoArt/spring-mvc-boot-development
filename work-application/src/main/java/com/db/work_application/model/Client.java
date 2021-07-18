package com.db.work_application.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static com.db.work_application.utils.ObjectUtil.asJsonString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private long id;
    @NotNull
    @Length(min = 1, max = 60)
    private String firstName;
    @NotNull
    @Length(min = 1, max = 60)
    private String lastName;

    @Override
    public String toString() {
        return asJsonString(this);
    }
}
