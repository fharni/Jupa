package de.falkharnisch.web.jupa.database;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date startDate;

    private Date endDate;

    private String topic;

    private String place;

    @OneToOne
    private Club club;

    @OneToOne
    private User instructor;

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getTopic() {
        return topic;
    }

    public String getPlace() {
        return place;
    }

    public Club getClub() {
        return club;
    }

    public User getInstructor() {
        return instructor;
    }
}
