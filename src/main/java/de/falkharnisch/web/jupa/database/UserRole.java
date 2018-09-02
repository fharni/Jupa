package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private User user;

    @OneToOne
    private Role role;

    @OneToOne
    private Club relatedClub;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }
}
