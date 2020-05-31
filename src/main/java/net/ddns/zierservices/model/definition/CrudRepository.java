package net.ddns.zierservices.model.definition;

import java.util.List;
import net.ddns.zierservices.util.Pair;

/**
 *
 * @author zier
 */
public interface CrudRepository<T extends BaseModel> {
        
    T save(T t);
    Boolean delete(T t);
    Boolean delete(Long id);
    T find(Long id);
    List<T> findAll();
    List<T> executeNamedQuery(String namedQuery, Pair<String, Object>... pairs);
}
