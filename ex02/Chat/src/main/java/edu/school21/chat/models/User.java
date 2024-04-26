package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private ArrayList<Chatroom> listOfCreatedRooms;
    private ArrayList<Chatroom> listRoomsUserSocializes;


    public User() {}

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(Long id, String login, String password, ArrayList<Chatroom> listOfCreatedRooms, ArrayList<Chatroom> listRoomsUserSocializes) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.listOfCreatedRooms = listOfCreatedRooms;
        this.listRoomsUserSocializes = listRoomsUserSocializes;
    }

    public Long getId() {
        return id;
    }

    public String getLogin(){
        return login;
    }

    @Override
    public String toString(){
        return "{id=" + id +
                ", login=\" " + login +
                "\", password=" + password +
                "\", createdRooms=" + listOfCreatedRooms +
                "\", rooms=" + listRoomsUserSocializes + "}";
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(listOfCreatedRooms, user.listOfCreatedRooms) &&
                Objects.equals(listRoomsUserSocializes, user.listRoomsUserSocializes);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, login, password);
    }
}
