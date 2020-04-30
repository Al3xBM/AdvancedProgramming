package factory;

import entity.ChartEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class chartDAO {
    public abstract void create(ChartEntity e);

    public abstract ChartEntity findById(int id);

    public abstract List<ChartEntity> findByName(String name);

    public abstract List<String> getArtistRankings();

}
