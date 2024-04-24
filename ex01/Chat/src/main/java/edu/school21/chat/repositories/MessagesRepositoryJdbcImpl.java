package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
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
            int idMessage = resultSet.getInt(1);
            Long idUser = resultSet.getLong(2);
            User user = findUserById(idUser);
            Long idChatroom = resultSet.getLong(3);
            Chatroom chatroom = findChatroomById(idChatroom, user);
            return Optional.of(new Message(id, user, chatroom, resultSet.getString(4), resultSet.getTimestamp(5).toLocalDateTime()));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private User findUserById(Long id){
        String sqlForUser = "SELECT * FROM chat.users WHERE id = " + id + ";";
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

    private Chatroom findChatroomById(Long id, User user){
        String sqlForChatroom = "SELECT * FROM chat.rooms WHERE id = " + id + ";";
        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery(sqlForChatroom);
            if(!resultSet.next()) {
                return null;
            }
            return new Chatroom(id, resultSet.getString(2), user);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
