package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int displayId;

    @OneToOne
    private Federation federation;

    public String getName() {
        return name;
    }

    public Federation getFederation() {
        return federation;
    }

    @Override
    public String toString() {
        return name + " (" + displayId + ")";
    }
}
