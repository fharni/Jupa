package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class Grading implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer sortOrder;

    @OneToOne
    private Discipline discipline;

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }
}
