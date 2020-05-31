
package net.ddns.zierservices.factory;

import net.ddns.zierservices.model.definition.BaseModel;
import net.ddns.zierservices.model.definition.CrudRepository;
import net.ddns.zierservices.model.repository.Repository;

public class RepositoryFactory {
    
    private RepositoryFactory() {
    }
    
    public static <T extends BaseModel> CrudRepository<T> create(Class<T> cls) {
        return new Repository<T>(cls);
    }
}
