package factory;

public abstract class Implementation {
    public abstract artistDAO getArtistDAO();
    public abstract albumDAO getAlbumDAO();
    public abstract chartDAO getChartDAO();
}
