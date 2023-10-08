package org.zoo_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnirestException, JsonProcessingException {
        boolean flag = true;
        Client client = new Client();
        Scanner reader = new Scanner(System.in);

        while(flag){
            System.out.println("Авторизация:");
            String login = reader.nextLine();
            String password = reader.nextLine();
            client.authorization(login,password);
            System.out.println(client.getUserEntity().toString());
        }
    }
}