package net.ddns.zierservices.model.repository;

import java.util.List;
import javax.persistence.Query;
import net.ddns.zierservices.model.definition.BaseModel;
import net.ddns.zierservices.model.definition.CrudRepository;
import net.ddns.zierservices.util.HibernateHelper;
import net.ddns.zierservices.util.Pair;

public class Repository<T extends BaseModel> implements CrudRepository<T> {

    private Class<T> cls;

    public Repository(Class<T> persistentClass) {
        this.cls = persistentClass;
    }
    
    @Override
    public T save(T t) {

        return HibernateHelper.runInTransaction(em -> {

            if (t.getId() == null) {
                
                em.persist(t);
                
            } else {
                
                em.merge(t);
                
            }

            return t;
        });

    }

    @Override
    public Boolean delete(T t) {

        return HibernateHelper.runInTransaction(em -> {

            em.remove(t);

            return true;
        });

    }

    @Override
    public Boolean delete(Long id) {

        return HibernateHelper.runInTransaction(em -> {

            T t = em.find(cls, id);

            em.remove(t);

            return true;
        });

    }

    @Override
    public T find(Long id) {

        return HibernateHelper.runInSession(em -> {
            
            return em.find(cls, id);
            
        });

    }

    @Override
    public List<T> findAll() {

        return HibernateHelper.runInSession(em -> {
            
            return em.createQuery("from " + cls.getSimpleName()).getResultList();
            
        });

    }

    @Override
    public List<T> executeNamedQuery(String namedQuery, Pair<String, Object>... params) {

        return HibernateHelper.runInSession(em -> {

            Query query = em.createNamedQuery(namedQuery);

            if (params != null && params.length > 0)
                for(Pair<String, Object> pair : params)
                    query.setParameter(pair.getKey(), pair.getValue());

            return query.getResultList();

        });

    }

}
