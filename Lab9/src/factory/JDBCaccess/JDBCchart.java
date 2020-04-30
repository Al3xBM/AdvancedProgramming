package factory.JDBCaccess;

import entity.ChartEntity;
import factory.chartDAO;

import java.util.List;

public class JDBCchart extends chartDAO {
    public void create(ChartEntity e){}

    public  ChartEntity findById(int id){return null;}

    public List<ChartEntity> findByName(String name){return null;}

    public List<String> getArtistRankings(){return null;}

}
