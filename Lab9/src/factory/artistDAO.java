package factory;

import entity.ArtistsEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class artistDAO {
    public abstract void create(ArtistsEntity e);

    public abstract ArtistsEntity findById(int id);

    public abstract List<ArtistsEntity> findByName(String name);
}
