import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private String url="jdbc:mysql://localhost:3306/";
    private String user;
    private String password;

    public Connection connection;
    DBConnection(String dataBaseName, String userName, String password){

        url=url.concat(dataBaseName.trim());
        this.user=userName.trim();
        this.password=password.trim();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    public void connectionClose() throws SQLException{
        if(!connection.isClosed())
            connection.close();
    }
}

