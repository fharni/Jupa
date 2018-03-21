package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class Grading implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private int sortOrder;

    @OneToOne
    private Discipline discipline;

    @Override
    public Integer getId() {
        return id;
    }
}
