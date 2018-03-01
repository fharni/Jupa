package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int displayId;

    @OneToOne
    private District district;

    public String getName() {
        return name;
    }

    public District getDistrict() {
        return district;
    }

    @Override
    public String toString() {
        return name + " (" + displayId + ")";
    }
}
