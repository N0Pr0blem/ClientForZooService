package org.zoo_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.ArrayList;

public class Client {
    private UserEntity userEntity;
    private HttpResponse<String> response;
    private JasonParser jasonParser = new JasonParser();

    private String[] commands;

    public Client(){
        Unirest.setTimeouts(0, 0);
    }

    public int authorization(String login, String password) throws UnirestException, JsonProcessingException {
        response = Unirest.get("http://localhost:8081/users/logg-in?login="+login+"&password="+password).asString();
        if(response.getStatus()==200){
            userEntity = jasonParser.getUser(response.getBody());
            commands = (getUserEntity().getAccess().equals(UserEntity.Access.ADMIN)) ? UserEntity.AdminCommand : UserEntity.UserCommand;
        }
        return response.getStatus();
    }

    public int showAllUsers() throws UnirestException, JsonProcessingException {
        ArrayList<UserEntity> allUsers = new ArrayList<>();
        response = Unirest.get("http://localhost:8081/users").asString();
        if(response.getStatus()==200){
            allUsers = jasonParser.getAllUsers(response.getBody());
            System.out.println(allUsers);
        }
        return response.getStatus();
    }
    public UserEntity getUserEntity(){
        return userEntity;
    }

    public String[] getCommands(){
        return commands;
    }

    public void addUser(UserEntity userEntity) throws UnirestException, IOException {
        HttpResponse<String> response = Unirest.post("http://localhost:8081/users")
                .header("Content-Type", "application/json")
                .body(jasonParser.userToJson(userEntity))
                .asString();
    }

    public void deleteUser(Long aLong) throws UnirestException {
        HttpResponse<String> response = Unirest.delete("http://localhost:8081/users/"+aLong).asString();
    }
}
