package util;

import org.hibernate.annotations.common.reflection.XProperty;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// PersistenceUtil is a singleton
public class PersistenceUtil {
    private static PersistenceUtil instance = null;

    private EntityManagerFactory factory;

    // private constructor restricted to this class itself
    // initializes factory asa an EntityManagerFactory for MusicAlbumsPU
    private PersistenceUtil()
    {
        factory = Persistence.createEntityManagerFactory(
                        "MusicAlbumsPU");
    }

    // static method to create instance of Singleton class
    public static PersistenceUtil getInstance()
    {
        if (instance == null)
            instance = new PersistenceUtil();

        return instance;
    }

    // returns an Entity Manager
    public EntityManager getEntityMan(){
        EntityManager em = factory.createEntityManager();
        return em;
    }

    // called at the end to close factory
    public void closeFactory(){
        factory.close();
    }
}
