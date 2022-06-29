package org.gemseeker.inventory.data;

import org.gemseeker.inventory.Settings;
import org.gemseeker.inventory.Utils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public final class EmbeddedDatabase {

    private static EmbeddedDatabase instance;
    private Connection connection;
    private Properties properties;

    private EmbeddedDatabase() throws ClassNotFoundException, SQLException, ParserConfigurationException,
            SAXException, IOException {
        createProperties();
        open();
        createTables();
        update();
    }

    public static EmbeddedDatabase getInstance() throws ClassNotFoundException, SQLException, ParserConfigurationException,
            SAXException, IOException {
        if (instance == null) instance = new EmbeddedDatabase();
        return instance;
    }

    private void createProperties() throws ParserConfigurationException, SAXException, IOException {
        Settings settings = Settings.getInstance();
        properties = new Properties();
        properties.put("user", settings.get(Settings.Type.DATABASE, "user"));
        properties.put("password", settings.get(Settings.Type.DATABASE, "password"));
    }

    private void open() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        String dbUrl = "jdbc:h2:file:" + Utils.DATABASE_PATH;
        connection = DriverManager.getConnection(dbUrl, properties);
    }

    private void createTables() throws SQLException {
//        if (connection != null) {
//            for (String sql: DatabaseUtils.tables()) {
//                try (Statement statement = connection.createStatement()) {
//                    statement.executeQuery(sql);
//                }
//            }
//        }
    }

    private void update() throws SQLException {
//        if (connection != null) {
//            for (String sql: DatabaseUtils.updates()) {
//                try (Statement statement = connection.createStatement()) {
//                    statement.executeQuery(sql);
//                }
//            }
//        }
    }

    public void close() throws SQLException {
        if (connection != null) connection.close();
    }

    // ==========================================================================================================

    public User getUser(String username, String password) throws SQLException {
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format("SELECT * FROM users WHERE username='%s' AND password='%s' LIMIT 1",
                        username, password);
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt(1));
                    user.setUsername(rs.getString(2));
                    user.setPassword(rs.getString(3));
                    user.setAuthority(rs.getString(4));
                    return user;
                }
            }
        }
        return null;
    }

}
