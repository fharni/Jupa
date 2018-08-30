package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
@Table(name = "license_type")
public class LicenseType implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String type;

    @Override
    public Integer getId() {
        return null;
    }

    public String getType() {
        return type;
    }
}
