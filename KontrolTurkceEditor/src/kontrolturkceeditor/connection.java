package kontrolturkceeditor;



import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hasan Alp Zengin
 */
public class connection {
    public Connection connect;
    public connection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Connection");
        connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/kelimeler?autoReconnect=true&useSSL=false", "root", "");
    }
}
