package de.falkharnisch.web.jupa.database;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Configuration implements BaseEntity {

    @Id
    private Integer id;

    private String key;

    private String value;

    @Override
    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
