package de.falkharnisch.web.jupa.database;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Membership implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "CLUB_ID")
    private Club club;

    @Temporal(TemporalType.DATE)
    private Date beginDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(nullable = false)
    private Boolean mainClub = Boolean.TRUE;

    public Membership() {
    }

    public Membership(User user, Club club, Date beginDate) {
        this.user = user;
        this.club = club;
        this.beginDate = beginDate;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    Boolean getMainClub() {
        return mainClub;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
