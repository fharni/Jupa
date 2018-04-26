package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class Club implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 6, columnDefinition = "CHAR")
    private String displayId;

    @OneToOne
    private District district;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayId() {
        return displayId;
    }

    public District getDistrict() {
        return district;
    }

    @Override
    public String toString() {
        return name + " (" + displayId + ")";
    }
}
