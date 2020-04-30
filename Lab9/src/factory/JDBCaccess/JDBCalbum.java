package factory.JDBCaccess;

import entity.AlbumsEntity;
import factory.albumDAO;
import util.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCalbum extends albumDAO {
    public void create(AlbumsEntity e){
        try {
            String query = "insert into albums values( 4, ?, ?, ?)";
            // prepares query
            PreparedStatement prep = Database.getInstance().getConn().prepareStatement(query);
            // sets an argument in place of ? from the query String
            prep.setString(1, e.getName());
            prep.setInt(2, (int)e.getArtist_id());
            prep.setInt(3, Math.toIntExact(e.getReleaseYear()));
            // executes query
            prep.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public AlbumsEntity findById(int id){
        AlbumsEntity alb = new AlbumsEntity();
        try {
            String query = "select * from albums where id = ?";
            PreparedStatement prep = Database.getInstance().getConn().prepareStatement(query);
            prep.setInt(1, id);
            // executes query such that the result is remembered in rs
            ResultSet rs = prep.executeQuery();
            while( rs.next() )
                // creates new album with the data stored in rs
                alb.setId(rs.getInt(1));
                alb.setName(rs.getString(2));
                alb.setReleaseYear(Long.valueOf(rs.getInt(3)));
                alb.setArtist_id(rs.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alb;
    }

    public List<AlbumsEntity> findByName(String name){
        List<AlbumsEntity> alb = new ArrayList<>();
        try {

            String query = "select * from albums where name = ?";
            PreparedStatement prep = Database.getInstance().getConn().prepareStatement(query);
            prep.setString(1, name);
            // executes query such that the result is remembered in rs
            ResultSet rs = prep.executeQuery();
            while( rs.next() ){
                // creates new album with the data stored in rs
                AlbumsEntity ae = new AlbumsEntity();
                ae.setId(rs.getInt(1));
                ae.setName(rs.getString(2));
                ae.setReleaseYear(Long.valueOf(rs.getInt(3)));
                ae.setArtist_id(rs.getInt(4));
                // adds album to album list
                alb.add(ae);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alb;
    }

    public List<AlbumsEntity> findByArtist(String name){
        List<AlbumsEntity> alb = new ArrayList<>();
        try {
            String query = "select id from artists where name = ?";
            PreparedStatement prep = Database.getInstance().getConn().prepareStatement(query);
            prep.setString(1, name);
            // executes query such that the result is remembered in rs
            ResultSet rs = prep.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            String query2 = "select * from albums where artist_id = ?";
            PreparedStatement prep2 = Database.getInstance().getConn().prepareStatement(query2);
            prep2.setInt(1, id);
            // executes query such that the result is remembered in rs
            ResultSet rs2 = prep2.executeQuery();
            while( rs2.next() ){
                // creates new album with the data stored in rs
                AlbumsEntity ae = new AlbumsEntity();
                ae.setId(rs2.getInt(1));
                ae.setName(rs2.getString(2));
                ae.setReleaseYear(Long.valueOf(rs2.getInt(3)));
                ae.setArtist_id(rs2.getInt(4));
                // adds album to album list
                alb.add(ae);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alb;
    }

}
