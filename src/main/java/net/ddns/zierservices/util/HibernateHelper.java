package net.ddns.zierservices.util;

import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateHelper {

    private HibernateHelper() {

    }

    private static EntityManagerFactory emf = null;

    static {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("default");
    }
    
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static <T extends EntityManager, R> R runInTransaction(Function<T, R> function) {
        EntityManager em = HibernateHelper.getEntityManager();
        
        try {

            em.getTransaction().begin();

            R r = function.apply((T) em);

            em.getTransaction().commit();

            return r;

        } catch (Exception ex) {

            em.getTransaction().rollback();

            throw ex;

        }
    }

    public static <T extends EntityManager> void runInTransaction(Action<T> action) {
        EntityManager em = HibernateHelper.getEntityManager();
        
        try {

            em.getTransaction().begin();

            action.execute((T) em);

            em.getTransaction().commit();

        } catch (Exception ex) {

            em.getTransaction().rollback();

            throw ex;

        }
    }

    public static <T extends EntityManager, R> R runInSession(Function<T, R> function) {
        EntityManager em = HibernateHelper.getEntityManager();
        
        try {
            
            em.clear();

            return function.apply((T) em);

        } catch (Exception ex) {

            throw ex;

        }
    }

    public static <T extends EntityManager> void runInSession(Action<T> action) {
        EntityManager em = HibernateHelper.getEntityManager();
        
        try {

            em.clear();
            
            action.execute((T) em);

        } catch (Exception ex) {

            throw ex;

        }
    }
}
