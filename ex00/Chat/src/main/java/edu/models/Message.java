package edu.models;


import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private int id;
    private User author;
    private Chatroom chatroom;
    private String text;
    private LocalDateTime date;

    @Override
    public String toString(){
        return "Message id: " + id +
                "\nAuthor: " + author.toString() +
                "\nChatroom: " + chatroom.toString() +
                "\nText message: " + text +
                "\nDate: " + date;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        Message message = (Message) obj;
        return id == message.id &&
                author.equals(message.author) &&
                chatroom.equals(message.chatroom) &&
                Objects.equals(text, message.text) &&
                Objects.equals(date, message.date);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, text, date) + author.hashCode() + chatroom.hashCode();
    }
}
