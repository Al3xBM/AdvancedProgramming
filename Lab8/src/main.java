import java.sql.*;

public class main {
    public static void main(String args[]){
        Database db = Database.getInstance();
        db.setConn();
        ArtistController art = new ArtistController(db);
        AlbumController alb = new AlbumController(db);

        art.create("FatManSlim", "Freedom Land");
        art.findByName("FatManSlim");

        alb.create("wolf who cried boi", 1, 1999);
        alb.findByArtist(1);
    }
}
