package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDataSource {
    private static final HikariDataSource hikariDataSource;

    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariConfig.setUsername("brcarisa");
        hikariConfig.setPassword("");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public static HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
