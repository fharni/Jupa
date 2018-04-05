package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class District implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int displayId;

    @OneToOne
    private Federation federation;

    public Integer getId() {
        return id;
    }

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
