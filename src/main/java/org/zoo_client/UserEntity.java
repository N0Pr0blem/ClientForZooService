package org.zoo_client;

public class UserEntity {
    public enum Access{ADMIN,USER}

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Access access;

    public UserEntity(Long id, String name, String surname, String login, String password, Access access){
        this.id = id;
        this.name=name;
        this.surname = surname;
        this.login=login;
        this.password=password;
        this.access = access;
    }
    public UserEntity(){
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    @Override
    public String toString(){
        return "Name:"+name+"\nSurname:"+surname+"\nLogin:"+login+"\nAccess:"+access;
    }
}
