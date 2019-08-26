package de.falkharnisch.web.jupa.database;

import javax.persistence.*;

@Entity
public class License implements BaseEntity {

	private static final long serialVersionUID = -8012360547399239068L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer level;

    @OneToOne
    private LicenseType type;

    @Override
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
