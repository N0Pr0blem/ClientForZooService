package org.zoo_client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private Client client = new Client();
    private Scanner reader = new Scanner(System.in);

    public static void main(String[] args) throws UnirestException, IOException {
        boolean flag = true;
        boolean isLogin = true;
        Main main = new Main();

        while (flag) {
            isLogin = main.authorization();
            while (isLogin) {
                main.showAllCommand();
                int choice = main.makeChoice();
                isLogin = main.doCommand(choice);
            }
        }
    }

    private boolean doCommand(int choice) throws UnirestException, IOException {
        boolean status = true;
        if (client.getUserEntity().getAccess().equals(UserEntity.Access.ADMIN))
            switch (choice) {
                case 1 -> {
                    client.addUser(registration());
                }
                case 2 -> {
                    client.showAllUsers();
                }
                case 3 -> {
                    client.deleteUser(makeChoiceFromUsers());
                }
                case 4 -> {
                    status = false;
                }
            }
        else
            switch(choice){
                case 1->{
                    status = false;
                }
            }
        return status;
    }

    private Long makeChoiceFromUsers() {
        return reader.nextLong();
    }

    private UserEntity registration() {
        String name, surname, login, password, accessString;
        UserEntity.Access access;

        System.out.println("[Регистрация] Имя:");
        name = reader.next();
        System.out.println("[Регистрация] Фамилия:");
        surname = reader.next();
        System.out.println("[Регистрация] Логин:");
        login = reader.next();
        System.out.println("[Регистрация] пароль:");
        password = reader.next();
        System.out.println("[Регистрация] доступ:");
        accessString = reader.next();
        if (accessString.toLowerCase().equals("admin")) access = UserEntity.Access.ADMIN;
        else access = UserEntity.Access.USER;

        return new UserEntity(name, surname, login, password, access);
    }

    private int makeChoice() {
        boolean flag = true;
        int choice = 0;

        while (flag) {
            choice = reader.nextInt();
            if (choice <= client.getCommands().length && choice > 0) flag = false;
        }

        return choice;
    }

    public boolean authorization() throws UnirestException, JsonProcessingException {
        System.out.print("[Авторизация] Логин: ");
        String login = reader.next();
        System.out.print("[Авторизация] Пароль: ");
        String password = reader.next();

        return client.authorization(login, password) == 200;
    }

    public void showAllCommand() {
        String[] commands = client.getCommands();

        for (int i = 0; i < commands.length; i++) {
            System.out.println("[" + (i + 1) + "]" + commands[i]);
        }
    }
}