import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChartController {
    Connection conn;

    public ChartController(Database db){
        this.conn = db.getConn();
    }

    // inserts a new album in chart table
    public void create(int place, int album_id, String album_name, String artist_name) {

        try {
            String query = "insert into chart values( ?, ?, ?, ?)";
            // prepares query
            PreparedStatement prep = conn.prepareStatement(query);
            // sets an argument in place of ? in the query String
            prep.setInt(1, place);
            prep.setInt(2, album_id);
            prep.setString(3, album_name);
            prep.setString(4, artist_name);
            // executes query
            prep.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void findByArtist(String artist_name){

        try {
            String query = "select * from chart where artist_id = ?";
            PreparedStatement prep = conn.prepareStatement(query);
            prep.setString(1, artist_name);
            ResultSet rs = prep.executeQuery();
            while( rs.next() )
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // returns a list of all artists in chart table
    public List<String> artistsRankings(){
        List<String> artists = new ArrayList<String>();
        try {
            String query = "select artist_name from chart order by place";
            // prepares query
            PreparedStatement prep = conn.prepareStatement(query);
            // executes query such that the result is rememberd in rs
            ResultSet rs = prep.executeQuery();
            int i = 0;
            while( rs.next() ) {
                // for every row in result, a new string is added to the artists list
                artists.add( rs.getString(1) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }
}
