package de.falkharnisch.web.jupa.database;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * Entity class for holding the user object.
 */
@Entity
public class User implements BaseEntity {

	private static final long serialVersionUID = -5923454326198644422L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String forename;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate birthday;

    @OneToMany
    @JoinColumn(name = "USER_ID")
    private Set<Membership> memberships;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserRole> userRoles;

    public User() {
    }

    public User(String username, String password, String forename, String surname, String email, LocalDate birthday) {
        this.username = username;
        this.password = password;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Club getClub() {
        for (Membership m : memberships) {
            if (m.getMainClub()) {
                return m.getClub();
            }
        }
        return null;
    }

    public Set<Membership> getMemberships() {
        return memberships;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public String getFullname() {
        return getForename() + " " + getSurname();
    }

    @Override
    public String toString() {
        return getFullname() + " (" + getUsername() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
