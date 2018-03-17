package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class Club implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private int displayId;

    @OneToOne
    private District district;

    public Integer getId() {
        return id;
    }

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
