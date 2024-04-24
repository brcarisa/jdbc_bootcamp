package edu.school21.chat.models;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message {
    private long id;
    private User author;
    private Chatroom chatroom;
    private String text;
    private LocalDateTime date;
    private static  final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public Message(long id, User author, Chatroom chatroom, String text, LocalDateTime date) {
        this.id = id;
        this.author = author;
        this.chatroom = chatroom;
        this.text = text;
        this.date = date;
    }

    @Override
    public String toString(){
        return "Message : { \n" +
                "id=" + id +
                ",\nauthor=" + author +
                ",\nroom: " + chatroom +
                ",\ntext:\"" + text +
                "\",\nDate: " + date.format(FORMATTER);
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
