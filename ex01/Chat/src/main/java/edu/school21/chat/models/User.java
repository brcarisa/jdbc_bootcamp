package edu.school21.chat.models;

import java.util.LinkedList;
import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private LinkedList<Chatroom> listOfCreatedRooms;
    private LinkedList<Chatroom> listRoomsUserSocializes;


    public User(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
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
