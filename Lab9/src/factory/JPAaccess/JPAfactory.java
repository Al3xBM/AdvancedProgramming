package factory.JPAaccess;

import factory.Implementation;
import factory.albumDAO;
import factory.artistDAO;
import factory.chartDAO;

public class JPAfactory extends Implementation {
    @Override
    public artistDAO getArtistDAO() {
        return new JPAartist();
    }

    @Override
    public albumDAO getAlbumDAO() {
        return new JPAalbum();
    }

    @Override
    public chartDAO getChartDAO() {
        return new JPAchart();
    }
}
