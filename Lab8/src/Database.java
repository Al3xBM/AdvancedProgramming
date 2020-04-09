import java.sql.*;

public class Database {
    private static Database instance = null;

    Connection conn;

    private Database(){}

    public static Database getInstance(){
        if( instance == null ){
            instance = new Database();
        }
        return instance;
    }

    public void setConn(){
        String url = "jdbc:oracle:thin:@localhost:1521:xe" ;
        conn = null;
        try {
            conn= DriverManager.getConnection(
                    url, "musicalbums", "sql");
        } catch(SQLException e) {
            System.err.println("Cannot connect to DB: " + e);
        }
    }

    public Connection getConn(){
        return conn;
    }

    public void closeConn(){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
