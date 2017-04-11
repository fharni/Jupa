package de.falkharnisch.web.karip.services;

import de.falkharnisch.web.karip.database.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Service class for handling with the user object.
 */
public class UserService implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public List<User> getUsers() {
        return null;
    }
}
