package edu.school21.chat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    private final static String URL_DB = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USERNAME = "brcarisa";
    private final static String PASSWORD = "";
    private final static String PATH_TO_SCHEMA = "/Users/brcarisa/Desktop/Java_Bootcamp.Day05-2/src/ex00/Chat/src/main/resources/schema.sql";
    private final static String PATH_TO_DATA = "/Users/brcarisa/Desktop/Java_Bootcamp.Day05-2/src/ex00/Chat/src/main/resources/data.sql";


    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = connectionToDataBase(connection);
            System.out.println("Connection success!");
            executeQueries(connection, statement);
            connection.commit();
            connection.close();
        } catch (SQLException | IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("Insertion success");
    }

    private static Connection connectionToDataBase(Connection connection) throws SQLException{
        connection = DriverManager.getConnection(URL_DB, USERNAME, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }

    private static void executeQueries(Connection connection, Statement statement)throws SQLException, IOException {
        statement = connection.createStatement();
        List<String> DMLQueries = Files.readAllLines(Paths.get(PATH_TO_DATA));
        List<String> SchemaQueries = Files.readAllLines(Paths.get(PATH_TO_SCHEMA));
        for(String query : SchemaQueries){
            statement.executeUpdate(query);
        }
        for(String query : DMLQueries){
            statement.executeUpdate(query);
        }
        statement.close();
    }
}