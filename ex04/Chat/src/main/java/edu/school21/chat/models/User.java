package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> listOfCreatedRooms;
    private List<Chatroom> listRoomsUserSocializes;


    public User() {}

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public void addCreatedRoom(Chatroom chatroom){
        listOfCreatedRooms.add(chatroom);
    }

    public void addRoomsUserSocialize(Chatroom chatroom){
        listRoomsUserSocializes.add(chatroom);
    }

    public void initArrays(){
        listOfCreatedRooms = new ArrayList<>();
        listRoomsUserSocializes =  new ArrayList<>();
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
    public String toString() {
        String str = "User {id=" + id + ", login='" + login + "', password='" + password + "'";
        if (listOfCreatedRooms != null) {
            str += ",\n\tcreatedRooms=" + listOfCreatedRooms.size(); // изменить на вывод количества комнат
        }
        if (listRoomsUserSocializes != null) {
            str += ",\n\tusedRooms=" + listRoomsUserSocializes.size(); // изменить на вывод количества комнат
        }
        str += "}";
        return str;
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
