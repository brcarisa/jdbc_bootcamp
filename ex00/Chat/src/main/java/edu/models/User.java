package edu.models;

import java.util.LinkedList;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private LinkedList<Chatroom> listOfCreatedRooms;
    private LinkedList<Chatroom> listRoomsUserSocializes;

    @Override
    public String toString(){
        return "UserID: " + id +
                " Login: " + login +
                " Password: " + password +
                " Created Rooms: " + listOfCreatedRooms.toString() +
                " List of chatrooms where a user socializes: " + listRoomsUserSocializes.toString();
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
