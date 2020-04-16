import java.sql.*;
import java.sql.ResultSet;

public class ArtistController{
    Connection conn;

    public ArtistController(Database db){
        conn = db.getConn();
    }

    // inserts an artist into artists table
    public void create(String name, String country) {
        try {
            String query = "insert into artists values(3, ?, ?)";
            // prepares query
            PreparedStatement prep = conn.prepareStatement(query);
            // sets an argument in place of ? from the query String
            prep.setString(1, name);
            prep.setString(2, country);
            // executes query
            prep.execute();
            System.out.println("artist was inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // returns the id of the last inserted artist
    public int getLastID(){
        int retID = 1;
        try{
            String query = "select max(id) from artists";
            PreparedStatement prep = conn.prepareStatement(query);
            ResultSet rs = prep.executeQuery();
            rs.next();
            retID = rs.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return retID;
    }

    // finds an album by the artist's name
    public Artist findByName(String name){
        Artist art = new Artist();
        try {
            String query = "select * from artists where name = ?";
            PreparedStatement prep  = conn.prepareStatement(query);
            prep.setString(1, name);
            // executes query such that the result is remembered in rs
            ResultSet rs = prep.executeQuery();
            while( rs.next() )
                // creates an Artist with the data returned by the query
                art = new Artist(rs.getInt(1),rs.getString(2),rs.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return art;
    }

}

