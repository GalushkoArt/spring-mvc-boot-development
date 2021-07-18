package com.db.work_application.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class ObjectUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public static String asJsonString(Object object) {
        return mapper.writeValueAsString(object);
    }
}
