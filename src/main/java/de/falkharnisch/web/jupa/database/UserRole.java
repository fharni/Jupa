package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
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
