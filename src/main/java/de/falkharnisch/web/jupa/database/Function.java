package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class Function implements BaseEntity {

	private static final long serialVersionUID = -1036396229659957847L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String function;

    public Integer getId() {
        return id;
    }

    public String getFunction() {
        return function;
    }
}
