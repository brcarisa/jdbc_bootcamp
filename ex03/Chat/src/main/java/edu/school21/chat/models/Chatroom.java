package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Chatroom {
    private long id;
    private String name;
    private User owner;
    private ArrayList<Message> listMessages;

    public Chatroom(long id, String name, User owner, ArrayList arrayList) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.listMessages = new ArrayList<>();
    }

    public User getOwner() {
        return owner;
    }

    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "{id:" + id +
                ", name: " + name +
                ", creator: " + owner.toString() +
                ", messages=" + listMessages + "}";
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
