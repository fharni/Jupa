package de.falkharnisch.web.jupa.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Discipline implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
