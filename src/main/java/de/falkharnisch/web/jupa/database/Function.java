package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class Function implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String function;

    public Integer getId() {
        return id;
    }

    public String getFunction() {
        return function;
    }
}
