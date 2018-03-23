package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
@Table(name = "COURSE_USER")
public class CourseParticipant implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Course course;

    @OneToOne
    private User user;

    @OneToOne
    private Annotation annotation;

    @Override
    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Annotation getAnnotation() {
        return annotation;
    }
}
