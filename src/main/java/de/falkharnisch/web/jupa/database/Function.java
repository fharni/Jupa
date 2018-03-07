package de.falkharnisch.web.jupa.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Function {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String function;

    public String getFunction() {
        return function;
    }
}
