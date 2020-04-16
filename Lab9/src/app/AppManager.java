package app;

import entity.AlbumsEntity;
import repo.AlbumRepository;
import util.PersistenceUtil;

import java.util.List;

public class AppManager {
    public static void main(String[] args){

        AlbumRepository alb = new AlbumRepository();

        // AlbumsEntity, ArtistsEntity and ChartEntity automatically generated with Intellij Persistence plugin
        List<AlbumsEntity> albums = alb.findByName("Starfire");

        for( AlbumsEntity a : albums){
            System.out.println(a.getName());
        }

        PersistenceUtil.getInstance().closeFactory();
    }
}
