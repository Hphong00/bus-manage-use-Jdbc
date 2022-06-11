package connectDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectData {
    static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
    static final String user = "system";
    static final String password = "1";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
