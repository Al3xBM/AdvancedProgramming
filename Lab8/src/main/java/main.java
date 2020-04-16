import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;


public class main {
    public static void main(String args[]){
        Database db = Database.getInstance();
        db.setConn();
        ArtistController art = new ArtistController(db);
        AlbumController alb = new AlbumController(db);

        ChartController chart = new ChartController(db);

        // faker is used to generate fake data
        Faker faker = new Faker();
        int last_artID, last_albID;
        String alb_name, art_name;
        for(int i = 0; i < 10; ++i){
            // inserts a new artist
            art_name = faker.artist().name();
            art.create( art_name, faker.country().name());

            // creates an album who has as artist the one just inserted
            last_artID = art.getLastID();
            alb_name = faker.superhero().name();
            alb.create(alb_name, last_artID, faker.number().numberBetween(1970, 2020));

            // inserts the last created album in chart
            // chart table has a trigger set such that when a new album is inserted on a place
            // that is  already occupied, all albums from that place and below are knocked down
            // one place
            // trigger code is commented below
            last_albID = alb.getLastID();
            chart.create(1, last_albID, alb_name, art_name);
        }

        // gets a list of all artists inside Chart
        List<String> artistsNames = chart.artistsRankings();
        // creates a list of Artist type objects
        List<Artist> artists = new ArrayList<Artist>();
        // every name inside artistsNames is searched in artists table
        // findbyname returns an Artist with the corresponding info
        for( String i : artistsNames){
            artists.add(art.findByName(i));
        }

        // prints artist in their corresponding order according to chart
        int place = 1;
        for( Artist a : artists ){
            System.out.println(place + ". " + a.getName() + " " + a.getId() + " " + a.getCountry());
            ++place;
        }
    }
}


/*
    create or replace trigger chart_insert before insert on chart for each row
        declare
        smg integer;
        smt integer;
        id_dec integer;
        begin
        select count(*) into smg from chart where place = :new.place;
        smt := :new.place;
        if( smg > 0 ) then
        dbms_output.put_line('upon entering if, smg is '||smg);
        select album_id into id_dec from chart where place = :new.place;
        update chart set place = place + 1 where place = smt;
        smt := smt + 1;
        select count(*) into smg from chart where place = smt;
        while( smg > 1 )loop
        dbms_output.put_line(smg);
        select album_id into id_dec from chart where place = smt and album_id <> id_dec;
        update chart set place = place + 1 where place = smt and album_id = id_dec;
        smt := smt + 1;
        select count(*) into smg from chart where place = smt;
        end loop;
        end if;
        end;*/
