package de.falkharnisch.web.jupa.database;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_grading")
public class UserGrading implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Grading grading;

    private Date date;

    @Override
    public Integer getId() {
        return id;
    }
}
