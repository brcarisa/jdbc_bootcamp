package edu.school21.chat.program;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.JdbcDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private final static String URL_DB = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USERNAME = "brcarisa";
    private final static String PASSWORD = "";
    private final static String SCHEMA = "schema.sql";
    private final static String DATA = "data.sql";


    public static void main(String[] args) {
        updateJDBC(JdbcDataSource.getHikariDataSource(), SCHEMA);
        updateJDBC(JdbcDataSource.getHikariDataSource(), DATA);
        MessagesRepository repository = new MessagesRepositoryJdbcImpl(JdbcDataSource.getHikariDataSource());
        Scanner scanner = new Scanner(System.in);
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



    private static void updateJDBC(HikariDataSource jdbcDataSource, String sqlQuery){
        Connection connection = null;
        Statement statement = null;
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
}