package de.falkharnisch.web.jupa.database;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_grading")
public class UserGrading implements BaseEntity {

	private static final long serialVersionUID = 854947619463539075L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private User user;

    @OneToOne
    private Grading grading;

    private LocalDate date;

    @Override
    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
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
