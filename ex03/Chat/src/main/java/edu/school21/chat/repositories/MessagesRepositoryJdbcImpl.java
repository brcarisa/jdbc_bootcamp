package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private HikariDataSource dataSource;

    public MessagesRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        String sqlForMessage = "SELECT * FROM chat.message WHERE id = " + id;
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery(sqlForMessage);
            if(!resultSet.next()) {
                return Optional.empty();
            }
            Long idUser = resultSet.getLong(2);
            User user = findUserById(idUser);
            Long idChatroom = resultSet.getLong(3);
            Chatroom chatroom = findChatroomById(idChatroom);
            return Optional.of(new Message(id, user, chatroom, resultSet.getString(4), resultSet.getTimestamp(5).toLocalDateTime()));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message){
        User user = findUserById(message.getAuthor().getId());
        Chatroom chatroom = findChatroomById(message.getChatroom().getId());
        String localDateTime = "'null'";
        if(user == null) {
            throw new NotSavedSubEntityException("User not found");
        }
        if(chatroom == null) {
            throw new NotSavedSubEntityException("Chatroom not found");
        }
        if (message.getDate() != null) {
            localDateTime = "'" + Timestamp.valueOf(message.getDate()) + "'";
        }
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("INSERT INTO chat.message (author, chatroom, text, time) VALUES ('" + user.getId() + "', '" + chatroom.getId() + "', '" + message.getText() + "', "+ localDateTime + ") RETURNING id");
            if(!resultSet.next()) {
                throw new NotSavedSubEntityException("Error on INSERT statement");
            } else {
                Long messageId = resultSet.getLong(1);
                message.setId(messageId);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message){
        Long messageId = message.getId();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE chat.message SET text = '" + message.getText() + "' WHERE id =" + messageId);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private User findUserById(Long id){
        String sqlForUser = "SELECT * FROM chat.users WHERE id = " + id;
        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery(sqlForUser);
            if(!resultSet.next()) {
                return null;
            }
            return new User(id, resultSet.getString(2), resultSet.getString(3));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private Chatroom findChatroomById(Long id){
        String sqlForChatroom = "SELECT * FROM chat.rooms WHERE id = " + id;
        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery(sqlForChatroom);
            if(!resultSet.next()) {
                return null;
            }
            return new Chatroom(id, resultSet.getString(2), new User(), new ArrayList());
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
