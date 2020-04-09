import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumController {
    Connection conn;

    public AlbumController(Database db){
        conn = db.getConn();
    }

    public void create(String name, int artistID, int releaseYear) {

        try {
            String query = "insert into albums values( 4, ?, ?, ?)";
            PreparedStatement prep = conn.prepareStatement(query);
            prep.setString(1, name);
            prep.setInt(2, artistID);
            prep.setInt(3, releaseYear);
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void findByArtist(int artistID){

        try {
            String query = "select * from albums where artist_id = ?";
            PreparedStatement prep = conn.prepareStatement(query);
            prep.setInt(1, artistID);
            ResultSet rs = prep.executeQuery();
            while( rs.next() )
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
