package de.falkharnisch.web.jupa.database;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_grading")
public class UserGrading implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private User user;

    @OneToOne
    private Grading grading;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Override
    public Integer getId() {
        return id;
    }

    public Grading getGrading() {
        return grading;
    }

    public Date getDate() {
        return date;
    }
}
