package repo;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.swing.text.html.parser.Entity;
import java.util.List;


@NamedQueries({
        @NamedQuery(
                name="albumsByName",
                query = "select a from AlbumsEntity a where a.name like :name"),
        @NamedQuery(
                name="albumsByArtist",
                query = "select a from AlbumsEntity a where a.artist_id = :id"),
})


public class AlbumRepository {

    public void create(AlbumsEntity e){
        // gets an entitymanager by calling getentityman from persistenceutil
        EntityManager em = PersistenceUtil.getInstance().getEntityMan();
        em.getTransaction().begin();
        // saves data in database
        em.persist(e);
        em.flush();
        // closes entity manager
        em.close();
    }

    public AlbumsEntity findById(int id){
        // gets an entitymanager by calling getentityman from persistenceutil
        EntityManager em = PersistenceUtil.getInstance().getEntityMan();
        em.getTransaction().begin();
        // finds one album by id
        AlbumsEntity alb = em.find(AlbumsEntity.class, id);
        em.getTransaction().commit();
        // closes entity manager
        em.close();
        return alb;
    }

    public List<AlbumsEntity> findByName(String name){
        // gets an entity manager by calling getentityman from persistenceutil
        EntityManager em = PersistenceUtil.getInstance().getEntityMan();
        em.getTransaction().begin();
        // calls named query and stores result in albums
        List<AlbumsEntity> albums = em.createNamedQuery("albumsByName").setParameter("name", name).getResultList();
        // closes entity manager
        em.close();
        // returns AlbumsEntity list
        return albums;
    }

    public List<AlbumsEntity> findByArtist(String name){
        // gets an entity manager by calling getentityman from persistenceutil
        EntityManager em = PersistenceUtil.getInstance().getEntityMan();
        em.getTransaction().begin();
        // finds an artist with the name provided
        ArtistsEntity ar = em.find(ArtistsEntity.class, name);
        // gets artist's id with .getId() method
        long id = ar.getId();
        // searches albums by artist's id
        List<AlbumsEntity> albums = em.createNamedQuery("albumsByArtist").setParameter("id", id).getResultList();
        // return albums list
        return albums;
    }
}
