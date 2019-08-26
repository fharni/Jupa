package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
@Table(name = "COURSE_USER")
public class CourseParticipant implements BaseEntity {

	private static final long serialVersionUID = 8774095204908912765L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }
}
