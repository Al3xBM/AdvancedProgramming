package app;

import entity.AlbumsEntity;
import factory.Implementation;
import factory.JDBCaccess.JDBCalbum;
import factory.JDBCaccess.JDBCfactory;
import factory.JPAaccess.JPAfactory;
import factory.albumDAO;
import repo.AlbumRepository;
import repo.ChartsRepository;
import util.PersistenceUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class AppManager {
    public static void main(String[] args){

        AlbumRepository alb = new AlbumRepository();

        ChartsRepository chart = new ChartsRepository();

        List<String> rankings = chart.getArtistRankings();

        for( String s : rankings){
            System.out.println(s);
        }

        callFactory();

        PersistenceUtil.getInstance().closeFactory();
    }

    public static void callFactory(){
        try {
            Scanner scanner = new Scanner(new FileInputStream("input.txt"));
            String input = scanner.nextLine();
            Implementation imp = new JDBCfactory();
            if( input.equals("JDBC")){
                imp = new JDBCfactory();
                albumDAO albumdao = imp.getAlbumDAO();
                List<AlbumsEntity> alb = albumdao.findByName("Nova Strike");
                for( AlbumsEntity a : alb ){
                    System.out.println(a.getReleaseYear() + " " + a.getArtist_id() + " " + a.getName() );
                }
            }
            else{
                imp = new JPAfactory();
                albumDAO albumdao = imp.getAlbumDAO();
                List<AlbumsEntity> alb = albumdao.findByName("Nova Strike");
                for( AlbumsEntity a : alb ){
                    System.out.println(a.getReleaseYear() + " " + a.getArtist_id() + " " + a.getName() );
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
