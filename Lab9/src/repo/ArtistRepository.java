package repo;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.text.html.parser.Entity;
import java.util.List;


@NamedQuery(
        name="artistsByName",
        query="select a from ArtistsEntity a where a.name like :name"
)

public class ArtistRepository {

    public void create(Entity e){
        // gets an EntityManager from PersistenceUtil.getInstance().getEntityMan()
        EntityManager em = PersistenceUtil.getInstance().getEntityMan();
        em.getTransaction().begin();
        // save to database
        em.persist(e);
        em.flush();
        // close entity manager
        em.close();
    }

    public ArtistsEntity findById(int id){
        // gets an EntityManeger from PersistenceUtil.getInstance().getEntityMan()
        EntityManager em = PersistenceUtil.getInstance().getEntityMan();
        em.getTransaction().begin();
        // searches for one ArtistEntity in database
        ArtistsEntity art = em.find(ArtistsEntity.class, id);
        // commits transaction
        em.getTransaction().commit();
        em.close();
        // returns artist
        return art;
    }

    public List<ArtistsEntity> findByName(String name){
        // gets an EntityManeger from PersistenceUtil.getInstance().getEntityMan()
        EntityManager em = PersistenceUtil.getInstance().getEntityMan();
        em.getTransaction().begin();
        // calls named query to get all artists with name like argument
        List<ArtistsEntity> artists = em.createNamedQuery("artistsByName").setParameter("name", name).getResultList();
        em.close();
        // returns list
        return artists;
    }
}

