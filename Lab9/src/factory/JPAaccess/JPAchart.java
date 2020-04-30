package factory.JPAaccess;

import entity.ChartEntity;
import factory.chartDAO;

import java.util.List;

public class JPAchart extends chartDAO {
    @Override
    public void create(ChartEntity e) {

    }

    @Override
    public ChartEntity findById(int id) {
        return null;
    }

    @Override
    public List<ChartEntity> findByName(String name) {
        return null;
    }

    @Override
    public List<String> getArtistRankings() {
        return null;
    }
}
