import java.sql.*;

public class ArtistController{
    Connection conn;

    public ArtistController(Database db){
        conn = db.getConn();
    }

    public void create(String name, String country) {
        if( conn == null )
            System.out.println("it's fkcing null");
        try {
            String query = "insert into artists values(3, ?, ?)";
            PreparedStatement prep  = conn.prepareStatement(query);
            prep.setString(1, name);
            prep.setString(2, country);
            prep.execute();
            System.out.println("artist was inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

        public void findByName(String name){

            try {
                String query = "select * from artists where name = ?";
                PreparedStatement prep  = conn.prepareStatement(query);
                prep.setString(1, name);
                ResultSet rs = prep.executeQuery();
                while( rs.next() )
                    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

}
