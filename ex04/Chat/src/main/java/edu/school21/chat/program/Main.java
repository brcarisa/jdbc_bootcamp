package edu.school21.chat.program;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private final static String SCHEMA = "schema.sql";
    private final static String DATA = "data.sql";


    public static void main(String[] args) {
        updateJDBC(JdbcDataSource.getHikariDataSource(), SCHEMA);
        updateJDBC(JdbcDataSource.getHikariDataSource(), DATA);
        UsersRepository repository = new UsersRepositoryJdbclmpl(JdbcDataSource.getHikariDataSource());
        try {
            List<User> list = repository.findAll(0, 3);
            list.forEach(System.out::println);
        }catch (SQLException e){
            e.printStackTrace();
        }

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