package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection DBConInstance;
    private static Connection SQLCon;

    public static DBConnection getInstance() throws SQLException, IOException {
        if (DBConInstance == null) {
            DBConInstance = new DBConnection();
        }
        return DBConInstance;
    }

    public DBConnection() throws SQLException, IOException {
        String[] creds = properties();

        SQLCon = DriverManager.getConnection(creds[0], creds[1], creds[2]);
    }

    public Connection getConnection() {
        return SQLCon;
    }

    public static String[] properties() throws IOException, NumberFormatException {
        Properties props = new Properties();
        String fileName = "database.properties";
        InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("database.properties");

        if (is != null) {
            props.load(is);
        } else {
            throw new FileNotFoundException("Couldn't find database.properties at "+fileName);
        }

        return new String[] {props.getProperty("jdbc_url"), props.getProperty("jdbc_username"), props.getProperty("jdbc_password")};
    }

}
