package factory.JPAaccess;

import entity.ArtistsEntity;
import factory.artistDAO;

import java.util.List;

public class JPAartist extends artistDAO {
    @Override
    public void create(ArtistsEntity e) {

    }

    @Override
    public ArtistsEntity findById(int id) {
        return null;
    }

    @Override
    public List<ArtistsEntity> findByName(String name) {
        return null;
    }
}
