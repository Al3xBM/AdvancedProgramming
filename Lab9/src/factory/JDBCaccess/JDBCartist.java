package factory.JDBCaccess;

import entity.ArtistsEntity;
import factory.artistDAO;

import java.util.List;

public class JDBCartist extends artistDAO {
    public void create(ArtistsEntity e){}

    public ArtistsEntity findById(int id){return null;}

    public List<ArtistsEntity> findByName(String name){return null;}

}
