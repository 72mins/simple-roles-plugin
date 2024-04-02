package com.simpleroles.simpleroles.sql;

import com.simpleroles.simpleroles.CustomConfig;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private Connection connection;

    FileConfiguration config = CustomConfig.get();

    private final String host = config.getString("mysql.host");
    private final String port = config.getString("mysql.port");
    private final String database = config.getString("mysql.database");
    private final String username = config.getString("mysql.username");
    private final String password = config.getString("mysql.password");

    public boolean isConnected() {
        return connection != null;
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if (!isConnected()) {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false";

            connection = DriverManager.getConnection(url, username, password);
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
