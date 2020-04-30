package factory.JDBCaccess;

import factory.Implementation;
import factory.albumDAO;
import factory.artistDAO;
import factory.chartDAO;

public class JDBCfactory extends Implementation {
    @Override
    public artistDAO getArtistDAO() {
        return new JDBCartist();
    }

    @Override
    public albumDAO getAlbumDAO() {
        return new JDBCalbum();
    }

    @Override
    public chartDAO getChartDAO() {
        return new JDBCchart();
    }
}
