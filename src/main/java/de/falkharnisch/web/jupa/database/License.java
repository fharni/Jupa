package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class License implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
