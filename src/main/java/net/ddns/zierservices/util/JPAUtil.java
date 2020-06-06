package net.ddns.zierservices.util;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class JPAUtil {

    private static EntityManagerFactory emf = null;

    static {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("default");
    }

    @Produces
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
