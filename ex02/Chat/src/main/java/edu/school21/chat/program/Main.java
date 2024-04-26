package edu.school21.chat.program;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.JdbcDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private final static String SCHEMA = "schema.sql";
    private final static String DATA = "data.sql";


    public static void main(String[] args) {
        updateJDBC(JdbcDataSource.getHikariDataSource(), SCHEMA);
        updateJDBC(JdbcDataSource.getHikariDataSource(), DATA);
        MessagesRepository repository = new MessagesRepositoryJdbcImpl(JdbcDataSource.getHikariDataSource());

        User creator = new User(7L, "user", "user", new ArrayList(), new ArrayList());
        User author = creator;
        Chatroom room = new Chatroom(8L, "room", creator, new ArrayList());
        Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
        repository.save(message);
        System.out.println(message.getId());

        User creator1 = new User(1L, "Doni", "user", new ArrayList(), new ArrayList());
        User author1 = creator1;
        Chatroom room1 = new Chatroom(2L, "room%1", creator1, new ArrayList());
        Message message1 = new Message(null, author1, room1, "Wassup!", LocalDateTime.now());
        repository.save(message1);
        System.out.println(message1.getId());


//        User creator2 = new User(7L, "Ivan", "user", new ArrayList(), new ArrayList());
//        User author2 = creator;
//        Chatroom room2 = new Chatroom(8L, "room%2", creator, new ArrayList());
//        Message message2 = new Message(null, author, room, "Hello!", LocalDateTime.now());
//        repository.save(message);
//        System.out.println(message.getId());
//
//
//        User creator3 = new User(7L, "CalbIvan", "user", new ArrayList(), new ArrayList());
//        User author3 = creator;
//        Chatroom room3 = new Chatroom(8L, "room%3", creator, new ArrayList());
//        Message message3 = new Message(null, author, room, "Hello!", LocalDateTime.now());
//        repository.save(message);
//        System.out.println(message.getId());
    }



    private static void updateJDBC(HikariDataSource jdbcDataSource, String sqlQuery){
        Connection connection;
        Statement statement;
        try {
            connection = jdbcDataSource.getConnection();
            statement = connection.createStatement();
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(sqlQuery);
            Scanner scanner = new Scanner(inputStream).useDelimiter(";");
            while(scanner.hasNext()){
                statement.executeUpdate(scanner.next());
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void searchMessage(MessagesRepository repository, Scanner scanner){
        while (true) {
            System.out.print("Enter ID:> ");
            Long id = scanner.nextLong();
            if(id.equals(-42L)){
                System.exit(0);
            }
            if (repository.findById(id).isPresent()) {
                Optional<Message> message = repository.findById(id);
                if(message.isPresent()) {
                    System.out.println(message.get());
                } else {
                    System.out.println("Message not found");
                }
            } else {
                System.out.print("Invalid ID. Try again: ");
            }
        }
    }
}