package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UsersRepositoryJdbclmpl implements UsersRepository{
    private HikariDataSource dataSource;

    public UsersRepositoryJdbclmpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) throws SQLException {
        List<User> users = new ArrayList<>();
        int offset = page * size;

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("WITH need_users AS (SELECT * FROM chat.users LIMIT ? OFFSET ?)\n" +
                "SELECT need_users.id, chat.users.login, chat.users.password, chat.user_chatroom.chat_id, chat.rooms.name, 'participants' AS source\n" +
                "FROM need_users\n" +
                "JOIN chat.user_chatroom ON need_users.id = user_chatroom.user_id\n" +
                "JOIN chat.users ON need_users.id = users.id\n" +
                "JOIN chat.rooms ON user_chatroom.chat_id = rooms.id\n" +
                "UNION\n" +
                "SELECT need_users.id, need_users.login, need_users.password, chat.rooms.id, chat.rooms.name, 'owners' AS source\n" +
                "FROM need_users\n" +
                "JOIN chat.rooms ON need_users.id = rooms.owner\n" +
                "ORDER BY source");
        preparedStatement.setInt(1, size);
        preparedStatement.setInt(2, offset);

        ResultSet resultSet = preparedStatement.executeQuery();

        Set<Long> userIdSet = new HashSet<>();
        Set<Long> chatIdSet = new HashSet<>();
        Map<Long, User> userMap = new HashMap<>();
        Map<Long, Chatroom> chatroomMap = new HashMap<>();

        while (resultSet.next()) {
            User user;
            Chatroom chatroom;
            Long userId = resultSet.getLong("id");
            String userLogin = resultSet.getString("login");
            String password = resultSet.getString("password");
            if (!userIdSet.contains(userId)) {
                user = new User(userId, userLogin, password);
                user.initArrays();
                users.add(user);
                userIdSet.add(userId);
                userMap.put(userId, user);
            }

            Long chatId = resultSet.getLong("chat_id");
            String roomName = resultSet.getString("name");

            if(!chatIdSet.contains(chatId)) {
                chatroom = new Chatroom(chatId, roomName, userMap.get(userId), new ArrayList<>());
                chatIdSet.add(chatId);
                chatroomMap.put(chatId, chatroom);
            }

            if(resultSet.getString("source").equals("participants")){
                user = userMap.get(userId);
                user.addRoomsUserSocialize(chatroomMap.get(chatId));
            } else if(resultSet.getString("source").equals("owners")){
                user = userMap.get(userId);
                chatroomMap.get(chatId).setOwner(user);
                user.addCreatedRoom(chatroomMap.get(chatId));
                user.addRoomsUserSocialize(chatroomMap.get(chatId));
            }
        }
        return users;
    }
}
