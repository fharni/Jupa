package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

/**
 * Entity class for holding the federation.
 */

@Entity
public class Federation implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int displayId;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + displayId + ")";
    }
}
