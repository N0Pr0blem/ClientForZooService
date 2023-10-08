package org.zoo_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class JasonParser {
    private ObjectMapper objectMapper = new ObjectMapper();
    public UserEntity getUser(String json) throws JsonProcessingException {
        UserEntity userEntity = objectMapper.readValue(json,UserEntity.class);
        return userEntity;
    }
    public ArrayList<UserEntity> getAllUsers(String json) throws JsonProcessingException {
        ArrayList<UserEntity> allUsers = objectMapper.readValue(json,ArrayList.class);
        return allUsers;
    }

    public String userToJson(UserEntity user) throws IOException {
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter,user);

        return stringWriter.toString();
    }
}
