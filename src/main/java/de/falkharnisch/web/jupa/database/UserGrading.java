package de.falkharnisch.web.jupa.database;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_grading")
public class UserGrading implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private User user;

    @OneToOne
    private Grading grading;

    @Column
    private LocalDate date;

    @Override
    public Integer getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Grading getGrading() {
        return grading;
    }

    public void setGrading(Grading grading) {
        this.grading = grading;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
