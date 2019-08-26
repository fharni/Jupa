package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class Annotation implements BaseEntity {

	private static final long serialVersionUID = 4052119490125891886L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
