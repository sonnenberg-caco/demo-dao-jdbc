package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    public static Connection conn = null;

    public static Connection getConnection() {

        try {

            if (conn == null) {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");

                conn = DriverManager.getConnection(url, props);
            }

            return conn;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    public static void closeConnection() {

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    public static Properties loadProperties() {

        try (FileInputStream fStream = new FileInputStream("db.properties")) {

            Properties props = new Properties();
            props.load(fStream);
            return props;

        } catch (Exception e) {

            throw new DbException(e.getMessage());
        }
    }
}
