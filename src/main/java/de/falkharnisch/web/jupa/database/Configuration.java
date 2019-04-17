package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class Configuration implements BaseEntity {

	private static final long serialVersionUID = -97983691286135822L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false, length = 1024)
    private String value;

    @Override
    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
