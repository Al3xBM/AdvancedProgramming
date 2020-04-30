package repo;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import entity.ChartEntity;
import org.hibernate.annotations.Entity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.List;



public class ChartsRepository extends AbstractRepository<ChartEntity> {
    public void create(ChartEntity e){
        // gets an entitymanager by calling getentityman from persistenceutil
        EntityManager em = PersistenceUtil.getInstance().getEntityMan();
        em.getTransaction().begin();
        // saves data in database
        em.persist(e);
        em.flush();
        // closes entity manager
        em.close();
    }

    @Override
    public ChartEntity findById(int id) {
        // gets an entitymanager by calling getentityman from persistenceutil
        EntityManager em = PersistenceUtil.getInstance().getEntityMan();
        em.getTransaction().begin();
        // finds one album by id
        List<ChartEntity> chrs = em.createNamedQuery("albumByID").setParameter("id", id).getResultList();
        em.getTransaction().commit();

        ChartEntity chr = new ChartEntity();
        if( chrs.size() == 1 )
            chr = chrs.get(0);

        // closes entity manager
        em.close();
        return chr;
    }

    public List<ChartEntity> findByName(String name){
        // gets an entitymanager by calling getentityman from persistenceutil
        EntityManager em = PersistenceUtil.getInstance().getEntityMan();
        em.getTransaction().begin();
        // finds one album by id
        List<ChartEntity> chr = em.createNamedQuery("findByArtist").setParameter("artname", name).getResultList();
        em.getTransaction().commit();
        // closes entity manager
        em.close();
        return chr;
    }

    @SuppressWarnings({"JpaAttributeTypeInspection", "JpaAttributeMemberSignatureInspection"})
    public List<String> getArtistRankings(){
        // gets an entity manager by calling getentityman from persistenceutil
        EntityManager em = PersistenceUtil.getInstance().getEntityMan();
        em.getTransaction().begin();
        // calls named query and stores result in albums
        List<String> artists = em.createNamedQuery("artistRankings").getResultList();
        // closes entity manager
        em.close();
        // returns AlbumsEntity list
        return artists;
    }

}
