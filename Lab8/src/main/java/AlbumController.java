import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumController {
    Connection conn;

    public AlbumController(Database db){
        conn = db.getConn();
    }

    // insert an album into albums table
    public void create(String name, int artistID, int releaseYear) {

        try {
            String query = "insert into albums values( 4, ?, ?, ?)";
            // prepares query
            PreparedStatement prep = conn.prepareStatement(query);
            // sets an argument in place of ? from the query String
            prep.setString(1, name);
            prep.setInt(2, artistID);
            prep.setInt(3, releaseYear);
            // executes query
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // returns the id of the last inserted album
    public int getLastID(){
        int retID = 1;
        try{
            String query = "select max(id) from albums";
            PreparedStatement prep = conn.prepareStatement(query);
            ResultSet rs = prep.executeQuery();
            while( rs.next() )
            retID = rs.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return retID;
    }

    public Album findByArtist(int artistID){
        Album alb = new Album();
        try {
            String query = "select * from albums where artist_id = ?";
            PreparedStatement prep = conn.prepareStatement(query);
            prep.setInt(1, artistID);
            // executes query such that the result is remembered in rs
            ResultSet rs = prep.executeQuery();
            while( rs.next() )
                // creates new album with the data stored in rs
                alb = new Album(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alb;
    }
}
