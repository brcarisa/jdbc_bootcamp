package edu.models;

import java.util.LinkedList;
import java.util.Objects;

public class Chatroom {
    private int id;
    private String name;
    private User owner;
    private LinkedList<Message> listMessages;

    @Override
    public String toString(){
        return "Chatroom ID: " + id +
                " Chatroom name: " + name +
                " User: " + owner.toString() +
                " Messages: " + listMessages.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        Chatroom chatroom = (Chatroom) obj;
        return id == chatroom.id &&
                Objects.equals(name, chatroom.name) &&
                owner.equals(chatroom.owner) &&
                Objects.equals(listMessages, chatroom.listMessages);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, listMessages) + owner.hashCode();
    }
}
