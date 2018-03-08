package de.falkharnisch.web.jupa.database;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ROLE_FUNCTION",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "FUNCTION_ID"))
    private Set<Function> functions;

    public String getRole() {
        return role;
    }

    public Set<Function> getFunctions() {
        return functions;
    }
}