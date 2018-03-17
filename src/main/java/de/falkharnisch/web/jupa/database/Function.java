package de.falkharnisch.web.jupa.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Function implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String function;

    public Integer getId() {
        return id;
    }

    public String getFunction() {
        return function;
    }
}
