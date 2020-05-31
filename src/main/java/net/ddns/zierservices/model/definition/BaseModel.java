package net.ddns.zierservices.model.definition;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
