package factory;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class albumDAO {
    public abstract void create(AlbumsEntity e);

    public abstract AlbumsEntity findById(int id);

    public abstract List<AlbumsEntity> findByName(String name);

    public abstract List<AlbumsEntity> findByArtist(String name);
}
