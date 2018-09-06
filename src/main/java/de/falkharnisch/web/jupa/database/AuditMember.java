package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
@Table(name = "AUDIT_USER")
public class AuditMember implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Audit audit;

    @OneToOne
    private User user;

    @OneToOne
    private Grading grading;

    private Boolean passed;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
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

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
}
