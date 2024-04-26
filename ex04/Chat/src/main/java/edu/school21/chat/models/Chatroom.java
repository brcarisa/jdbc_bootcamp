package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Chatroom {
    private long id;
    private String name;
    private User owner;
    private List<Message> listMessages;

    public Chatroom(long id, String name, User owner, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.listMessages = messages;
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

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "\n\t\tChatroom {id=" + id + ", title='" + name + "', \n\t\t\towner=" + owner.getLogin() + ", \n\t\t\tmessages=" + listMessages + "}";
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
