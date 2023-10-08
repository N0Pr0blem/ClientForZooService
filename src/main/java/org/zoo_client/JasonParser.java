package org.zoo_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JasonParser {
    private ObjectMapper objectMapper = new ObjectMapper();
    public UserEntity getObject(String json) throws JsonProcessingException {
        UserEntity userEntity = new UserEntity();

        userEntity = objectMapper.readValue(json,UserEntity.class);
        return userEntity;
    }
}
