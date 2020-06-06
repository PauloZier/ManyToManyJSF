package net.ddns.zierservices.repository;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import net.ddns.zierservices.entity.definition.BaseEntity;
import net.ddns.zierservices.util.Pair;

@Named
@RequestScoped
public class Repository implements Serializable {

    @Inject
    private EntityManager entityManager;
    
    public <T extends BaseEntity> T save(T t) {

        try {
            
            entityManager.getTransaction().begin();
            
            if (t.getId() == null) {

                entityManager.persist(t);

            } else {

                entityManager.merge(t);

            }
            
            entityManager.getTransaction().commit();
            
            return t;
            
        } catch (Exception ex) {
            
            entityManager.getTransaction().rollback();
            
            throw ex;
        }

    }

    public <T extends BaseEntity> Boolean delete(T t) {

        try {
            
            entityManager.getTransaction().begin();
            
            entityManager.remove(t);

            entityManager.getTransaction().commit();
            
            return true;
            
        } catch (Exception ex) {
            
            entityManager.getTransaction().rollback();
            
            throw ex;
            
        }

    }

    public Boolean delete(Class cls, Long id) {

       try {
            
            entityManager.getTransaction().begin();
            
            entityManager.remove(entityManager.find(cls, id));

            entityManager.getTransaction().commit();
            
            return true;
            
        } catch (Exception ex) {
            
            entityManager.getTransaction().rollback();
            
            throw ex;
            
        }

    }

    public <T extends BaseEntity> T find(Class<T> cls, Long id) {
        
        try {

            entityManager.clear();
            
            return (T) entityManager.find(cls, id);

        } catch (Exception ex) {
    
            throw ex;
            
        }

    }

    public <T extends BaseEntity> List<T> findAll(Class<T> cls) {

        try {
            
            return entityManager.createQuery("from " + cls.getSimpleName()).getResultList();

        } catch (Exception ex) {
        
            throw ex;
        }

    }

    public List executeNamedQuery(String namedQuery, Pair<String, Object>... params) {

        try {
            
            Query query = entityManager.createNamedQuery(namedQuery);

            if (params != null && params.length > 0) {
                for (Pair<String, Object> pair : params) {
                    query.setParameter(pair.getKey(), pair.getValue());
                }
            }

            return query.getResultList();

        } catch (Exception ex) {
            
            throw ex;
            
        }

    }

}
