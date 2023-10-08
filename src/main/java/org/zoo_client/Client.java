package org.zoo_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Client {
    private UserEntity userEntity;
    private HttpResponse<String> response;
    private JasonParser jasonParser = new JasonParser();

    public Client(){
        Unirest.setTimeouts(0, 0);
    }

    public int authorization(String login, String password) throws UnirestException, JsonProcessingException {
        response = Unirest.get("http://localhost:8081/users/logg-in?login="+login+"&password="+password).asString();
        if(response.getStatus()==200){
            userEntity = jasonParser.getObject(response.getBody());
        }
        System.out.println(response.getBody());
        return response.getStatus();
    }
    public UserEntity getUserEntity(){
        return userEntity;
    }
}
